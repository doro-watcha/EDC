package edc.app.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DittoFeature(

    @SerializedName("lamp")
    val lamp: DittoLamp
) : Serializable