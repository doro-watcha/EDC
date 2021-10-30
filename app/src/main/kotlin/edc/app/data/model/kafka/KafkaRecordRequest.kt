package edc.app.data.model.kafka

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import edc.app.data.model.Thing
import java.io.Serializable

data class KafkaRecordRequest(
    @SerializedName("records")
    val records : List<KafkaRecord>
) : Serializable
