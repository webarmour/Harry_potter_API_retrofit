package com.example.harry_potter_and_retrofit.data.paging.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


const val BASE_URL = "https://api.potterdb.com/"

interface SearchCharactersPagingApi {

    @GET("v1/characters")
    suspend fun getCharacters(): com.example.harry_potter_and_retrofit.data.paging.dto.Response

}

object RetrofitInstance {
    private val retrofit1 = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    val searchCharactersPagingApi: SearchCharactersPagingApi =
        retrofit1.create(SearchCharactersPagingApi::class.java)
}