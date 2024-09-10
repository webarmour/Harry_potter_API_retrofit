package com.example.harry_potter_and_retrofit.data.network

import com.example.harry_potter_and_retrofit.data.network.dto.CharacterDataTransferObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL = "https://harry-potter-api-en.onrender.com/characters"

interface SearchCharactersApi {

    @GET()
    suspend fun getCharacters(): List<CharacterDataTransferObject>

    @GET()
    suspend fun getCharactersById(@Query("id") id: Int = 1): CharacterDataTransferObject

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