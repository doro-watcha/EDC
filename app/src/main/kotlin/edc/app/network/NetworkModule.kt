package edc.app.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object NetworkModule {

    private val BASE_URL = "http://210.97.42.250:8088"
    private val CONNECT_TIMEOUT = 15L
    private val WRITE_TIMEOUT = 15L
    private val READ_TIMEOUT = 15L


    val interceptor = Interceptor { chain ->
        chain.proceed(chain.request().newBuilder().apply {
            addHeader(
                "X-CSRF-TOKEN",
                "UL GWayzZBRovmOwdyxNBbE1qpuKFEIKjGM2mauWAVGTmixbQnWCNkeinn9stsmU5TAXVL6aMkdth0zgDt70cyBECx17miZV5+0B6LECUg35MI="
            )
        }.build())
    }

    val client = OkHttpClient.Builder().apply {
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        retryOnConnectionFailure(true)
        addInterceptor(interceptor)
        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
    }.build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build()


}