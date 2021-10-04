package edc.app.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DittoProperties(

    @SerializedName("on")
    val on: Boolean,

    @SerializedName("color")
    val color: String
) : Serializable