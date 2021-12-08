package edc.app.data.model.kafka

import com.google.gson.annotations.SerializedName
import edc.app.data.model.Thing
import java.io.Serializable

data class KafkaRecord(

    @SerializedName("value")
    val value: TopicData
) : Serializable