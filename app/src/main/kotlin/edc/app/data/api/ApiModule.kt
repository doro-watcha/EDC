package edc.app.data.api

import edc.app.network.edc.EdcRetrofit
import edc.app.network.kafka.KafkaRetrofit

object ApiModule {

    /**
     *
     * EDC API
     */

    private val edc = EdcRetrofit.retrofit

    val thingAPI = edc.create(ThingAPI::class.java)

    val authAPI = edc.create(AuthAPI::class.java)


    /**
     * Kafka API
     */
    private val kafka = KafkaRetrofit.retrofit

    val kafkaAPI = kafka.create(KafkaAPI::class.java)


}