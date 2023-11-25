//package com.example.hackathon.feature.remote
//
//import android.widget.ImageView
//import com.example.hackathon.domain.User
//import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
//import okhttp3.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.Body
//import retrofit2.http.Field
//import retrofit2.http.FormUrlEncoded
//import retrofit2.http.GET
//import retrofit2.http.POST
//import retrofit2.http.Path
//
//interface RestCountriesApi {
//
//    @POST("login")
//    suspend fun login(
//        @Body request: User
//    ): Response<User>
//}
//
//val retrofit = Retrofit.Builder()
//    .baseUrl("https://restcountries.com/v3.1/")
//    .addConverterFactory(GsonConverterFactory.create())
//    .addCallAdapterFactory(CoroutineCallAdapterFactory())
//    .build()
//
//
