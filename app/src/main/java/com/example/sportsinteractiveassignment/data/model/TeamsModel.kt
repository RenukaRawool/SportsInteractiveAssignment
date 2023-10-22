package com.example.sportsinteractiveassignment.data.model

import com.google.gson.annotations.SerializedName

data class TeamsModel(
    val teamsData: Map<String, TeamsDataModel>?
)

data class TeamsDataModel(
    @SerializedName("Name_Full") var countryNameFull : String,
    @SerializedName("Name_Short") var countryNameShort : String,
    @SerializedName("Players") var Players : PlayersDataModel?
)

data class PlayersDataModel(
    val playersData: Map<String, PlayersDetailsModel>?
)

data class PlayersDetailsModel(
    @SerializedName("Position") var position : String,
    @SerializedName("Name_Full") var nameFull : String,
    @SerializedName("Iskeeper") var isKeeper : Boolean,
    @SerializedName("Batting") var batting : PlayerBattingDetails,
    @SerializedName("Bowling") var bowling : PlayerBowlingDetails
)

data class PlayerBattingDetails(
    @SerializedName("Style") var BattingStyle : String,
    @SerializedName("Average") var average : String,
    @SerializedName("Strikerate") var strikeRate : String,
    @SerializedName("Runs") var runs : String
)

data class PlayerBowlingDetails(
    @SerializedName("Style") var bowlingStyle : String,
    @SerializedName("Average") var average : String,
    @SerializedName("Economyrate") var economyRate : String,
    @SerializedName("Wickets") var wickets : String

)