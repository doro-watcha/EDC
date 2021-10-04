package edc.app.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DittoLamp(

    @SerializedName("properties")
    val properties: DittoProperties
) : Serializable