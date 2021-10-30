package edc.app.data.api

import com.google.gson.annotations.SerializedName
import edc.app.data.model.Thing
import retrofit2.Call
import retrofit2.http.*
import java.io.Serializable

interface ThingAPI {

    @GET("v1.0/sites/{siteId}/things")
    fun listThings(
        @Path("siteId") siteId : String
    ) : Call<ThingListResponse>
}


data class ThingListResponse(
    @SerializedName("things")
    val things : List<Thing>
) : Serializable