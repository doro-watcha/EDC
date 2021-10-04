package edc.app.network.edc

import edc.app.data.api.ThingAPI

object EDCApi {

    val thingApi = EdcRetrofit.retrofit.create(ThingAPI::class.java)
}