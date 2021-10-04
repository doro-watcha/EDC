package edc.app.data.api

import edc.app.data.api.ditto.DittoThingAPI
import edc.app.network.ditto.DittoRetrofit
import edc.app.network.edc.EdcRetrofit

object ApiModule {

    private val edc = EdcRetrofit.retrofit

    val thingAPI = edc.create(ThingAPI::class.java)

    val authAPI = edc.create(AuthAPI::class.java)

    private val ditto = DittoRetrofit.retrofit

    val dittoThingAPI = ditto.create(DittoThingAPI::class.java)


}