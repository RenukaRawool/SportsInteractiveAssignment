package com.example.sportsinteractiveassignment.data.model

import com.google.gson.annotations.SerializedName


data class DataModel(

    @SerializedName("Matchdetail")
    val  matchDetail : MatchDetailsModel?,

    @SerializedName("Nuggets")
    val  nuggets : List<String>?,

    @SerializedName("Innings")
    val  innings : List<InningsModel>?,

    @SerializedName("Teams")
    val  teams : Map<String, TeamsDataModel>?,

    @SerializedName("Notes")
    val  notes : NotesModel?,

    )
