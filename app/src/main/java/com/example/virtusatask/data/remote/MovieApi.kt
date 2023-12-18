package com.example.virtusatask.data.remote

import com.example.virtusatask.BuildConfig
import com.example.virtusatask.constants.Constants.LANGUAGE
import com.example.virtusatask.data.dto.MovieDetailsDto
import com.example.virtusatask.data.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("{lang}/API/ComingSoon/{apiKey}")
    suspend fun getMoviesList(
        @Path("lang") lang: String = LANGUAGE,
        @Path("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): MovieListDto

    @GET("{lang}/API/Title/{apiKey}/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: String,
        @Path("lang") lang: String = LANGUAGE,
        @Path("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): MovieDetailsDto
}