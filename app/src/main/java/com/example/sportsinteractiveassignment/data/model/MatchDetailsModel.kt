package com.example.sportsinteractiveassignment.data.model

import com.google.gson.annotations.SerializedName

data class MatchDetailsModel(

    @SerializedName("Team_Home") var teamHome : String?,
    @SerializedName("Team_Away") var teamAway : String?,
    @SerializedName("Match") var Match : MatchDataModel?,
    @SerializedName("Series") var Series : SeriesDataModel?,
    @SerializedName("Venue") var Venue : VenueDataModel?,
    @SerializedName("Officials") var Officials : OfficialsDataModel?,
    @SerializedName("Weather") var Weather : String?,
    @SerializedName("Tosswonby") var Tosswonby : String?,
    @SerializedName("Status") var Status : String?,
    @SerializedName("Status_Id") var StatusId : String?,
    @SerializedName("Player_Match") var PlayerMatch : String?,
    @SerializedName("Result") var Result : String?,
    @SerializedName("Winningteam") var Winningteam : String?,
    @SerializedName("Winmargin") var Winmargin : String?,
    @SerializedName("Equation") var Equation : String?
)

data class MatchDataModel(
    @SerializedName("Livecoverage") var liveCoverage : String?,
    @SerializedName("Id") var id : String?,
    @SerializedName("Code") var code : String?,
    @SerializedName("League") var league : String?,
    @SerializedName("Number") var number : String?,
    @SerializedName("Type") var type : String?,
    @SerializedName("Date") var date : String?,
    @SerializedName("Time") var time : String?,
    @SerializedName("Offset") var offset : String?,
    @SerializedName("Daynight") var dayNight : String?
)


data class SeriesDataModel(
    @SerializedName("Id") var id : String?,
    @SerializedName("Name") var name : String?,
    @SerializedName("Status") var status : String?,
    @SerializedName("Tour") var tour : String?,
    @SerializedName("Tour_Name") var tourName : String?
)

data class VenueDataModel(
    @SerializedName("Id") var id : String?,
    @SerializedName("Name") var name : String?
)

data class OfficialsDataModel(
    @SerializedName("Umpires") var umpires : String?,
    @SerializedName("Referee") var referee : String?
)
