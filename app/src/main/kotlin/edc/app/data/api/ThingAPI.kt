package edc.app.data.api

import com.google.gson.annotations.SerializedName
import edc.app.data.model.Thing
import edc.app.data.model.edc.Attr
import retrofit2.Call
import retrofit2.http.*
import java.io.Serializable

interface ThingAPI {

    @GET("v1.0/sites/{siteId}/things/{thingName}/attrs")
    fun fetchThingAttrs(
        @Path("siteId") siteId : String,
        @Path("thingName") thingName : String
    ) : Call<ThingAttrsResponse>
}


data class ThingAttrsResponse(
    @SerializedName("thingName")
    val thingName : String? = null,

    @SerializedName("attrs")
    val attrs : List<Attr>? = null ,

    @SerializedName("code")
    val code : String? = null ,

    @SerializedName("message")
    val message : String? = null ,

    @SerializedName("createdAt")
    val createdAt : String? = null
) : Serializable