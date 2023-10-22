package com.example.sportsinteractiveassignment.data.network

import com.example.sportsinteractiveassignment.data.model.MatchModel
import retrofit2.http.GET

interface ApiService {
    @GET("/v3/683cc57f-b379-46ad-889e-42b9297b8031")
    suspend fun getNotesDetails(): MatchModel?
}