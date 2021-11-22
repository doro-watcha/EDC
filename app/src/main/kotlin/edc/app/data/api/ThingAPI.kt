package edc.app.data.api

import com.google.gson.annotations.SerializedName
import edc.app.data.model.Thing
import edc.app.data.model.edc.Attr
import retrofit2.Call
import retrofit2.http.*
import java.io.Serializable

interface ThingAPI {

    @GET("v1.0/sites/{siteId}/things/{thingName}/attrs")
    fun listThings(
        @Path("siteId") siteId : String,
        @Path("thingName") thingName : String
    ) : Call<ThingListResponse>
}


data class ThingListResponse(
    @SerializedName("thingName")
    val thingName : String,

    @SerializedName("attrs")
    val attrs : List<Attr>
) : Serializable