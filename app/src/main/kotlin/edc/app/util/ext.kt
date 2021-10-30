package edc.app.util

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

fun<T> Call<T>.responseTo(callback: CallBackKt<T>.() -> Unit) {
    val callBackKt = CallBackKt<T>()
    callback.invoke(callBackKt)
    this.enqueue(callBackKt)
}

class CallBackKt<T>: Callback<T> {

    var onResponse: ((T?) -> Unit) = {}
    var onFailure: ((t: Throwable?) -> Unit)? = null

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure?.invoke(t)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onResponse.invoke(response.body())
    }

}


fun<T> Response<T>.respond() = this.body()



fun <T> Observable<T>.addSchedulers(): Observable<T> {
    return this.subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread())
}


fun rxRepeatTimer(tick: Long, onNext: (t: Long) -> Unit, initialDelay : Long? = 0): Disposable {
    return Observable.interval(initialDelay ?: 0L, tick, TimeUnit.MILLISECONDS)
        .addSchedulers()
        .subscribe({
            onNext(it)
        }, {

        })
}

fun Disposable.disposedBy(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}