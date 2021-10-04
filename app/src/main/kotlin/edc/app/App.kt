/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package edc.app

import edc.app.data.api.ApiModule
import edc.app.data.api.ThingListResponse
import edc.app.data.model.*
import edc.app.util.AppPreference
import edc.app.util.AppPreference.AUTH_TOKEN
import edc.app.util.AppPreference.USER_ID
import edc.app.util.AppPreference.USER_PASSWORD
import edc.app.util.responseTo
import edc.utilities.StringUtils

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun main() {

//    val body = HashMap<String, String>()
//    body["userId"] = USER_ID
//    body["userPassword"] = USER_PASSWORD
//    val request = ApiModule.authAPI.userLogin(body)
//    println(request)
//    request.responseTo {
//        onResponse = {
//            AUTH_TOKEN = "UL " + it.authToken
//        }
//
//    }
//
//    ApiModule.thingAPI.listThings("C000000003").responseTo {
//        onResponse = {
//
//        }
//    }

    val dittoPath = "org.eclips.ditto:test-doro"

    val dittoBody = HashMap<String,String>()
    dittoBody["thingId"] = dittoPath
    dittoBody["policyId"] = dittoPath
    dittoBody["definition"] = "testDoro:TestDoro:1.1.1"
//    dittoBody["attributes"] = DittoAttribute("test","test").toString()
//    dittoBody["features"] = DittoFeature(DittoLamp(DittoProperties(true,"blue"))).toString()
    ApiModule.dittoThingAPI.putFancyCar(dittoPath,dittoBody).responseTo {
        onResponse = {
            println(it)
        }
        onFailure = {
            println(it)
        }
    }


}

