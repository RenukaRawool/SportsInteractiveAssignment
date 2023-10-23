package com.example.sportsinteractiveassignment.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sportsinteractiveassignment.data.Repository
import com.example.sportsinteractiveassignment.data.model.MatchModel
import com.example.sportsinteractiveassignment.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MatchViewModel(var repository: Repository) : ViewModel() {

    var matchDetails = MutableLiveData<Resource<MatchModel>?>()

    fun getMatchDetails() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getMatchData()
            withContext(Dispatchers.Main){
                when(response){
                    is Resource.Success -> {
                        matchDetails.postValue(Resource.Success(response.data))
                    }

                    is Resource.Error -> {
                        matchDetails.postValue(Resource.Error(response.errorMessage))
                    }

                    Resource.NoInternet -> {
                        matchDetails.postValue(Resource.NoInternet)
                    }

                    Resource.Unknown -> {
                        matchDetails.postValue(Resource.Unknown)
                    }
                }
            }
        }
    }
}