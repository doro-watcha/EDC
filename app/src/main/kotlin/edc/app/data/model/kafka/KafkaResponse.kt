package edc.app.data.model.kafka

import com.google.gson.annotations.SerializedName

data class KafkaResponse(
    @SerializedName("offsets")
    val offsets : List<KafkaOffset>,

    @SerializedName("key_schema_id")
    val keySchemaId : Int?,

    @SerializedName("value_schema_id")
    val valueSchemaId : Int?
)
