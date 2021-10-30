package edc.app.data.api

import edc.app.data.model.kafka.KafkaRecordRequest
import edc.app.data.model.kafka.KafkaResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface KafkaAPI {

    @POST("/topics/{topic}")
    fun insertToTopic(
        @Path("topic") topic : String,
        @Body kafkaRecord: KafkaRecordRequest,
        @Header("Accept") accept : String,
        @Header("Content-Type") contentType : String
    ) : Call<KafkaResponse>
}