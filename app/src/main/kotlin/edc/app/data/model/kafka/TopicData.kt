package edc.app.data.model.kafka

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TopicData(
    @SerializedName("data")
    val data : String,

    @SerializedName("aliasName")
    val aliasName : String,

    @SerializedName("createdAt")
    val createdAt : String
) : Serializable