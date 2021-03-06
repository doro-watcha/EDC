import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class EdcThing(

    @SerializedName("thingName")
    val thingName: String,

    @SerializedName("thingDuration")
    val thingDuration : Int,

    @SerializedName("dbName")
    val dbName : String,
    
    @SerializedName("aliasName")
    val aliasName : String

) : Serializable