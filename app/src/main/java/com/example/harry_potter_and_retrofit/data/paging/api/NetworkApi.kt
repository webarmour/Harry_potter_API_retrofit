package com.example.harry_potter_and_retrofit.data.paging.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL = "https://api.potterdb.com/"

//https://api.potterdb.com/v1/characters?page[number]=1
interface SearchCharactersPagingApi {

    @GET("v1/characters")
    suspend fun getCharacters(@Query("page[number]") page: Int = 1): com.example.harry_potter_and_retrofit.data.paging.dto.Response

}

object RetrofitInstance {
    private val retrofit1 = Retrofit.Builder()
        .client(
            OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            }).build()
        )
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    val searchCharactersPagingApi: SearchCharactersPagingApi =
        retrofit1.create(SearchCharactersPagingApi::class.java)
}