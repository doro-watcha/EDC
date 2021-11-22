package edc.app.data.model.edc

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AttrValue(
    @SerializedName("attrValue")
    val attrValue : String = "",

    @SerializedName("createdAt")
    val createdAt : String = ""
) : Serializable
