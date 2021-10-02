package edc.app.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Thing(

    @SerializedName("thingId")
    val thingId: String
) : Serializable