package edc.app.data.api.ditto

import edc.app.data.api.ThingListResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface DittoThingAPI {

    @PUT("api/2/things/{path}")
    fun putFancyCar(
        @Path("path") path : String,
        @Body params : HashMap<String,String>
    ) : Call<ThingListResponse>
}