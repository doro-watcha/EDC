package edc.app.data.api

import edc.app.data.model.User
import retrofit2.Call
import retrofit2.http.*

interface AuthAPI {

    @POST("v1.1/main/action/itaUserLogin")
    fun userLogin(
        @Body params : HashMap<String,String>
    ) : Call<User>
}

