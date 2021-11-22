package edc.app.data.model.edc

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class EdcThingList(
    @SerializedName("thingList")
    val thingList: List<EdcSetting>
) : Serializable