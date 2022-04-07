package edc.app.network.edc

import edc.app.util.AppPreference
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object EdcRetrofit {

    private val BASE_URL = "https://61.75.178.52:28088"
    private val CONNECT_TIMEOUT = 15L
    private val WRITE_TIMEOUT = 15L
    private val READ_TIMEOUT = 15L


    val interceptor = Interceptor { chain ->
        chain.proceed(chain.request().newBuilder().apply {
            addHeader("X-CSRF-TOKEN", AppPreference.AUTH_TOKEN)
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
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()





}