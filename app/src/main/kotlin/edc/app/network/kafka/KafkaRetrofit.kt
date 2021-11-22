package edc.app.network.kafka

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object KafkaRetrofit {

    private val BASE_URL = "http://localhost:8082"
    private val CONNECT_TIMEOUT = 15L
    private val WRITE_TIMEOUT = 15L
    private val READ_TIMEOUT = 15L



    val client = OkHttpClient.Builder().apply {
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        retryOnConnectionFailure(true)
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