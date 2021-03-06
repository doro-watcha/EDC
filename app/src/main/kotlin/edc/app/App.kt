/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package edc.app

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import edc.app.data.api.ApiModule
import edc.app.data.model.*
import edc.app.data.model.edc.EdcSetting
import edc.app.data.model.edc.EdcThingList
import edc.app.data.model.kafka.KafkaRecord
import edc.app.data.model.kafka.KafkaRecordRequest
import edc.app.data.model.kafka.TopicData
import edc.app.util.AppPreference.AUTH_TOKEN
import edc.app.util.AppPreference.USER_ID
import edc.app.util.AppPreference.USER_PASSWORD
import edc.app.util.disposedBy
import edc.app.util.responseTo
import edc.app.util.rxRepeatTimer
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.io.FileReader
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private val compositeDisposable = CompositeDisposable()

fun main() {

    try {
        val jsonString = FileReader("edcSetting.json").readText()
        val edcSetting  = JsonParser.parseString(jsonString).asJsonObject

        val edcThingList = Gson().fromJson(edcSetting["thingList"] , EdcThingList::class.java)
        val siteId = Gson().fromJson(edcSetting["siteId"], String::class.java)

        /**
         * 쓰레드 생성을 위한 Null API Request
         */
        initToken(edcThingList,siteId)


    } catch (e: IOException) {
        println(e)
        println(e.message)
        println("ERROR OCCURED")
    }
}

/**
 * API 날리기전 Auth API
 */

fun initToken ( edcThingList : EdcThingList, siteId : String ) {
    val body = HashMap<String, String>()
    body["userId"] = USER_ID
    body["userPassword"] = USER_PASSWORD
    val request = ApiModule.authAPI.userLogin(body)
    request.responseTo {
        onResponse = {
            AUTH_TOKEN = "UL " + it?.authToken

            pollingServer(edcThingList,siteId)
        }
    }
}

fun pollingServer(edcThingList : EdcThingList, siteId : String  ) {

    edcThingList.thingList.forEach{ setting ->
        rxRepeatTimer(setting.thingDuration * 1L,{
            fetchEdcData(setting.thingName, siteId, setting.dbName , setting.aliasName, setting.attrKey )
        }).disposedBy(compositeDisposable)
    }
}

fun fetchAuthToken( thingName : String , siteId : String , topic : String, alisName : String , attrKey : String   ) {

    val body = HashMap<String, String>()
    body["userId"] = USER_ID
    body["userPassword"] = USER_PASSWORD
    val request = ApiModule.authAPI.userLogin(body)
    request.responseTo {
        onResponse = {
            AUTH_TOKEN = "UL " + it?.authToken

            fetchEdcData(thingName, siteId, topic, alisName, attrKey )
        }
    }
}

fun fetchEdcData ( thingName : String, siteId : String , topic : String , alisName : String , attrKey : String  ) {
    ApiModule.thingAPI.fetchThingAttrs( siteId , thingName).responseTo {
        onResponse = {
            println(it)
            if ( it == null) {
                fetchAuthToken(thingName, siteId, topic, alisName ,attrKey )
            }
            else {

                it.attrs?.forEach { attr ->
                    if ( attr.attrKey == attrKey ) {
                        insertToKafka(attr.attrValueList[0].attrValue, topic, alisName )
                    }
                }

            }
        }
        onFailure = {
            println("FUCK!!!")
        }
    }
}

fun insertToKafka(data : String, topic: String, aliasName : String ) {

    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초")
    val formattedTime = current.format(formatter)

    val kafkaRecordRequest = KafkaRecordRequest(records = listOf(KafkaRecord(value = TopicData ( data = data, aliasName = aliasName, createdAt = formattedTime))))

    ApiModule.kafkaAPI.insertToTopic(
        topic,
        kafkaRecordRequest,
        "application/vnd.kafka.v2+json",
        "application/vnd.kafka.json.v2+json"
    ).responseTo {
        onResponse = {

        }
    }
}