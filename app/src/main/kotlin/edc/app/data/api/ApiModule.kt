package edc.app.data.api

import edc.app.network.NetworkModule

object ApiModule {

    val thingAPI = NetworkModule.retrofit.create(ThingAPI::class.java)
}