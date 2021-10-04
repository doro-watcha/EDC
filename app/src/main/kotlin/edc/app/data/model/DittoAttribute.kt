package edc.app.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DittoAttribute(

    @SerializedName("manufacturer")
    val manufacturer: String,

    @SerializedName("VIN")
    val VIN: String
) : Serializable