package at.ac.fhcampuswien.corostats.data.api

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://corona-virus-stats.herokuapp.com/api/v1/cases/general-stats/"

object CoronaVirusStatsClient {
    fun getClient(): CoronaVirusStatsInterface {
        val requestInterceptor = Interceptor { chain ->
            val url: HttpUrl = chain.request()
                .url()
                .newBuilder()
                .build()
            val request: Request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)
        }

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(3, TimeUnit.SECONDS).build()


        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoronaVirusStatsInterface::class.java)
    }
}