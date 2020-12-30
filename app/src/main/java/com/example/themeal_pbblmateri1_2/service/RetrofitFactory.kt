package com.example.themeal_pbblmateri1_2.service
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
object RetrofitFactory {
    const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    fun create(): RetrofitService {
        //Inisalisasi builder
        val builder = OkHttpClient().newBuilder()
        builder.connectTimeout(15, TimeUnit.SECONDS)
        //create Logging to watch Log
        var logging =  HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.HEADERS
        logging.level = HttpLoggingInterceptor.Level.BODY
        //adding interceptor
        builder.addInterceptor(logging)
        //create Client
        var client = builder.build()
        //setting Retrofit
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
        return  retrofit.create(RetrofitService::class.java)
    }
}