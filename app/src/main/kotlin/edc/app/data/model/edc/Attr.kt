package edc.app.data.model.edc

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Attr(
    @SerializedName("attrKey")
    val attrKey : String = "",

    @SerializedName("attrValues")
    val attrValueList : List<AttrValue>
) : Serializable
