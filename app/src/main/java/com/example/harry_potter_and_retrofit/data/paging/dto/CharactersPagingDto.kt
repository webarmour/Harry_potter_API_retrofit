package com.example.harry_potter_and_retrofit.data.paging.dto

import androidx.resourceinspection.annotation.Attribute
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Response(
    @Json(name="data")
    val data:List<DataJson>
)

@JsonClass(generateAdapter = true)
data class DataJson(
    val id: String,
    @Json(name = "attributes")
    val attribute: Attributes
)

@JsonClass(generateAdapter = true)
data class Attributes(
    @Json(name = "name")
    val name: String?,
    @Json(name = "blood_status")
    val bloodStatus: String?,
    @Json(name = "house")
    val hogwartsHouse: String?,
    @Json(name = "patronus")
    val patronus: String?,
    @Json(name = "image")
    val imageUrl: String?,


)
