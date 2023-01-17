package com.example.finalproject1_11.Model

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    var retrofit: Retrofit? = null


    fun getClient(): Retrofit?{
        val gson = GsonBuilder()
            .setLenient()
            .create()
        //http://universities.hipolabs.com/search?name=Middlesex
        retrofit = Retrofit.Builder()
            .baseUrl("http://universities.hipolabs.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit
    }
}