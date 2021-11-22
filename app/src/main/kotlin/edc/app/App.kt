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
import edc.app.util.AppPreference.AUTH_TOKEN
import edc.app.util.AppPreference.USER_ID
import edc.app.util.AppPreference.USER_PASSWORD
import edc.app.util.disposedBy
import edc.app.util.responseTo
import edc.app.util.rxRepeatTimer
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.concurrent.TimeUnit

private val compositeDisposable = CompositeDisposable()
fun main() {

    val path = Paths.get("edcSetting.json")

    try {
//        val jsonString = Files.readString(path).trimIndent()
//        val edcSetting  = JsonParser.parseString(jsonString).asJsonObject
//
//        val edcThingList = Gson().fromJson(edcSetting["thingList"] , EdcThingList::class.java)
//        val siteId = Gson().fromJson(edcSetting["siteId"], String::class.java)

        fetchAuthToken()
        listTopicForTest()
    } catch (e: IOException) {

    }
}

/**
 * API 날리기전 Auth API
 */

fun fetchAuthToken(  ) {

    val body = HashMap<String, String>()
    body["userId"] = USER_ID
    body["userPassword"] = USER_PASSWORD
    val request = ApiModule.authAPI.userLogin(body)
    println(request)
    request.responseTo {
        onResponse = {
            AUTH_TOKEN = "UL " + it?.authToken
            println(AUTH_TOKEN)
            //listThings(thingName, siteId)
        }
    }
}

fun listThings( thingName : String, siteId : String  ) {
    ApiModule.thingAPI.listThings( siteId , thingName).responseTo {
        onResponse = {
            println(it)
//
//            it?.attrs?.forEach { attr ->
//                attr.attrValueList.forEach { attrValue ->
//                    println(attrValue.attrValue)
//                }
//            }
        }
    }

}

fun insertToKafka(thing: Thing, topic: String) {

    val kafkaRecordRequest = KafkaRecordRequest(records = listOf(KafkaRecord(value = thing)))

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


fun listTopicForTest() {

    ApiModule.kafkaAPI.listTopics().responseTo {
        onResponse = {
            it?.forEach { topic ->
                print("$topic ")
            }
        }
    }
}
