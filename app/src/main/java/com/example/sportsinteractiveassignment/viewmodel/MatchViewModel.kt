package com.example.sportsinteractiveassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import com.example.sportsinteractiveassignment.data.Repository
import com.example.sportsinteractiveassignment.data.model.MatchModel
import com.example.sportsinteractiveassignment.data.model.PlayersDetailsModel
import com.example.sportsinteractiveassignment.data.model.TeamsDataModel
import com.example.sportsinteractiveassignment.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MatchViewModel(var repository: Repository) : ViewModel() {

    val matchDetails : LiveData<Resource<MatchModel>?> get() = _matchDetails.distinctUntilChanged()
    private val _matchDetails = MutableLiveData<Resource<MatchModel>?>()
    var playerDetails = MutableLiveData<List<PlayersDetailsModel>?>()

    fun getMatchDetails() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getMatchData()
            withContext(Dispatchers.Main) {
                when (response) {
                    is Resource.Success -> {
                        _matchDetails.postValue(Resource.Success(response.data))
                    }

                    is Resource.Error -> {
                        _matchDetails.postValue(Resource.Error(response.errorMessage))
                    }

                    Resource.NoInternet -> {
                        _matchDetails.postValue(Resource.NoInternet)
                    }

                    Resource.Unknown -> {
                        _matchDetails.postValue(Resource.Unknown)
                    }
                }
            }
        }
    }

    fun getPlayerDetails(teamModel: Map<String, TeamsDataModel>?) {
        val playerList = mutableListOf<PlayersDetailsModel>()

        teamModel?.forEach{
            it.value.Players?.values?.toList()?.forEach {playDetailItem->
                playDetailItem.country = it.value.countryNameShort
                playerList.add(playDetailItem)
            }
        }
        playerDetails.postValue(playerList)
    }

    fun clearData(){
        playerDetails.value == null
    }
}