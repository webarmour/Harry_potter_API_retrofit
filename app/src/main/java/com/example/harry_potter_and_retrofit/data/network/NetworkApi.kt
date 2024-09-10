package com.example.harry_potter_and_retrofit.data.network

import com.example.harry_potter_and_retrofit.data.network.dto.CharacterDataTransferObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


const val BASE_URL = "https://harry-potter-api-en.onrender.com/"

interface SearchCharactersApi {

    @GET("characters")
    suspend fun getCharacters(): List<CharacterDataTransferObject>

    @GET("characters/{id}")
    suspend fun getCharactersById(@Path("id") id: Int = 1): CharacterDataTransferObject

    // @GET("/{id}")
    // suspend fun getCharactersByIdPath(@Path("id") id: Int = 1)

}

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    val searchCharactersApi: SearchCharactersApi = retrofit.create(SearchCharactersApi::class.java)
}