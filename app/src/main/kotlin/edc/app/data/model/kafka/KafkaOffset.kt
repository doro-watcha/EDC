package edc.app.data.model.kafka

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class KafkaOffset(
    @SerializedName("partition")
    val partition : Int,

    @SerializedName("offset")
    val offset : Int ,

    @SerializedName("error_code")
    val errorCode : Int?,

    @SerializedName("error")
    val error : String?
) : Serializable
