package edc.app.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User (
    @SerializedName("userId")
    val userId : String,

    @SerializedName("userPassword")
    val userPassword : String,

    @SerializedName("authToken")
    val authToken : String
) : Serializable