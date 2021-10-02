package edc.app.util

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun<T> Call<T>.responseTo(callback: CallBackKt<T>.() -> Unit) {
    val callBackKt = CallBackKt<T>()
    callback.invoke(callBackKt)
    this.enqueue(callBackKt)
}

class CallBackKt<T>: Callback<T> {

    var onResponse: ((T) -> Unit) = {}
    var onFailure: ((t: Throwable?) -> Unit)? = null

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure?.invoke(t)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onResponse.invoke(response.body()!!)
    }

}


fun<T> Response<T>.respond() = this.body()
