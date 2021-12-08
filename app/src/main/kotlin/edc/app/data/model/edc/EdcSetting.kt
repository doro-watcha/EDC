package edc.app.data.model.edc


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class EdcSetting(

    @SerializedName("thingName")
    val thingName : String,

    @SerializedName("thingDuration")
    val thingDuration : Int,

    @SerializedName("dbName")
    val dbName : String,

    @SerializedName("aliasName")
    val aliasName : String,

    @SerializedName("attrKey")
    val attrKey : String
) : Serializable