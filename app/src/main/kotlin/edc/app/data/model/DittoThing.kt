package edc.app.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DittoThing(

    @SerializedName("thingId")
    val thingId: String,

    @SerializedName("policyId")
    val policyId: String,

    @SerializedName("definition")
    val definition: String,

    @SerializedName("attributes")
    val attributes: DittoAttribute

//    @SerializedName("feature")
//    val features : String
) : Serializable