package edc.app.data.model.edc


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class EdcSetting(

    @SerializedName("siteId")
    val siteId: String,

    @SerializedName("thingList")
    val thingList: EdcThingList
) : Serializable