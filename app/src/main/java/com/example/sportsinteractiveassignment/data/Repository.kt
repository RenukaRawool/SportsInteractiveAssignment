package com.example.sportsinteractiveassignment.data

import android.net.Network
import com.example.sportsinteractiveassignment.data.model.MatchModel
import com.example.sportsinteractiveassignment.data.network.ApiService
import com.example.sportsinteractiveassignment.utils.NetworkManager
import com.example.sportsinteractiveassignment.utils.Resource
import java.lang.Exception

class Repository(private val apiService: ApiService, private val network: NetworkManager) {
    suspend fun getMatchData():Resource<MatchModel>{
        return if (network.isNetworkAvailable()){
            try {
                val response = apiService.getMatchData()
                response?.let {
                    Resource.Success(it)
                }?: run {
                    Resource.Error("Something went wrong!")
                }
            }catch (ex:Exception){
                Resource.Unknown
            }
        }
        else{
            Resource.NoInternet
        }
    }
}