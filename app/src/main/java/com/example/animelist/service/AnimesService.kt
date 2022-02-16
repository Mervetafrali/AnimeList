package com.example.animelist.service

import retrofit2.Call
import retrofit2.http.GET
import com.example.animelist.model.Animes
import retrofit2.http.Header
import retrofit2.http.Headers

interface AnimesService {
    @Headers(
            "x-rapidapi-host: top-anime.p.rapidapi.com",
            "x-rapidapi-key: 8f25130ae9mshc77412b5a92e9dcp1c5156jsnf5f24d33548e"
        )
    @GET("all")
    fun getAnimeList(): Call<List<Animes>>
}