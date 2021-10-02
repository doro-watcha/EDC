package edc.app.data.api

import edc.app.network.NetworkModule

object ApiModule {

    val network = NetworkModule.retrofit

    val thingAPI = network.create(ThingAPI::class.java)

    val authAPI = network.create(AuthAPI::class.java)
}