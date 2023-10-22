package com.example.sportsinteractiveassignment.data.model

import com.google.gson.annotations.SerializedName

data class InningsModel(
    @SerializedName("Number") var number : String?,
    @SerializedName("Battingteam") var battingTeam : String?,
    @SerializedName("Total") var total : String?,
    @SerializedName("Wickets") var wickets : String?,
    @SerializedName("Overs") var overs : String?,
    @SerializedName("Runrate") var runRate : String?,
    @SerializedName("Byes") var byes : String?,
    @SerializedName("Legbyes") var legByes : String?,
    @SerializedName("Wides") var wides : String?,
    @SerializedName("Noballs") var noBalls : String?,
    @SerializedName("Penalty") var penalty : String?,
    @SerializedName("AllottedOvers") var allottedOvers : String?,
    @SerializedName("Batsmen") var batsMen : List<BatsmenDataModel>?,
    @SerializedName("Partnership_Current") var partnerShipCurrent : PartnershipCurrentDataModel?,
    @SerializedName("Bowlers") var bowlers : List<BowlersDataModel>?,
    @SerializedName("FallofWickets") var fallOfWickets : List<FallofWicketsDataModel>?,
    @SerializedName("PowerPlay") var powerPlay : PowerPlayDataModel?
)


data class BatsmenDataModel(
    @SerializedName("Batsman") var batsMan : String?,
    @SerializedName("Runs") var runs : String?,
    @SerializedName("Balls") var balls : String?,
    @SerializedName("Fours") var fours : String?,
    @SerializedName("Sixes") var sixes : String?,
    @SerializedName("Dots") var dots : String?,
    @SerializedName("Strikerate") var strikeRate : String?,
    @SerializedName("Dismissal") var dismissal : String?,
    @SerializedName("Howout") var howOut : String?,
    @SerializedName("Bowler") var bowler : String?,
    @SerializedName("Fielder") var fielder : String?
)

data class BowlersDataModel(
    @SerializedName("Bowler") var bowler : String?,
    @SerializedName("Overs") var overs : String?,
    @SerializedName("Maidens") var maidens : String?,
    @SerializedName("Runs") var runs : String?,
    @SerializedName("Wickets") var wickets : String?,
    @SerializedName("Economyrate") var economyRate : String?,
    @SerializedName("Noballs") var noBalls : String?,
    @SerializedName("Wides") var wides : String?,
    @SerializedName("Dots") var dots : String?
)

data class PartnershipCurrentDataModel(
    @SerializedName("Runs") var runs : String,
    @SerializedName("Balls") var balls : String,
    @SerializedName("Batsmen") var batsMen : List<BatsmenPartnerShipDataModel>
)

data class BatsmenPartnerShipDataModel(
    @SerializedName("Batsman") var batsMan : String?,
    @SerializedName("Runs") var runs : String?,
    @SerializedName("Balls") var balls : String?

)

data class FallofWicketsDataModel(
    @SerializedName("Batsman") var batsMan : String?,
    @SerializedName("Score") var score : String?,
    @SerializedName("Overs") var overs : String?
)

data class PowerPlayDataModel(
    @SerializedName("PP1") var pp1 : String,
    @SerializedName("PP2") var pp2 : String
)
