package edc.app.data.api

import edc.app.data.model.kafka.KafkaRecordRequest
import edc.app.data.model.kafka.KafkaResponse
import retrofit2.Call
import retrofit2.http.*

interface KafkaAPI {

    @POST("/topics/{topic}")
    fun insertToTopic(
        @Path("topic") topic : String,
        @Body kafkaRecord: KafkaRecordRequest,
        @Header("Accept") accept : String,
        @Header("Content-Type") contentType : String
    ) : Call<KafkaResponse>

    @GET("topics")
    fun listTopics() : Call<List<String>>
}