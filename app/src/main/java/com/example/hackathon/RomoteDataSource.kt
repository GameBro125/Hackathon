package com.example.hackathon

import android.widget.ImageView
import com.example.hackathon.domain.User
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RestCountriesApi {
    val user: User
    @POST("")
    suspend fun login(@Body user: User): Response<ResponseBody>
}

var retrofit = Retrofit.Builder()
    .baseUrl("http://dummy.restapiexample.com/api/v1/create")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var restCountriesApi: RestCountriesApi = retrofit.create(RestCountriesApi::class.java)
