package edc.app.network.ditto

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object DittoRetrofit {

    private val BASE_URL = "http://localhost:8080"
    private val CONNECT_TIMEOUT = 15L
    private val WRITE_TIMEOUT = 15L
    private val READ_TIMEOUT = 15L

    val client = OkHttpClient.Builder().apply {
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        retryOnConnectionFailure(true)
        addInterceptor(BasicAuthInterceptor("ditto","ditto"))
    }.build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    class BasicAuthInterceptor(user: String, password: String) : Interceptor {
        private val credentials: String

        override fun intercept(chain: Interceptor.Chain): Response {
            val request: Request = chain.request()
            val authenticatedRequest: Request = request.newBuilder()
                .header("Authorization", credentials).build()
            return chain.proceed(authenticatedRequest)
        }

        init {
            credentials = Credentials.basic(user, password)
        }
    }





}