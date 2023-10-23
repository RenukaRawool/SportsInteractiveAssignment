package com.example.sportsinteractiveassignment.utils

import android.widget.ImageView
import coil.ComponentRegistry
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.example.sportsinteractiveassignment.R


fun String?.getCountryFlag():String{


    return countryMap[this].orEmpty()
}

val countryMap = mapOf("IND" to "https://cdn.jsdelivr.net/npm/country-flag-emoji-json@2.0.0/dist/images/IN.svg",
    "NZ" to "https://cdn.jsdelivr.net/npm/country-flag-emoji-json@2.0.0/dist/images/NZ.svg",
    "PAK" to "https://cdn.jsdelivr.net/npm/country-flag-emoji-json@2.0.0/dist/images/PK.svg",
    "SA" to "https://cdn.jsdelivr.net/npm/country-flag-emoji-json@2.0.0/dist/images/ZA.svg")

fun ImageView.setImage(url:String){
    val imageLoader = ImageLoader.Builder(this.context)
        .components{
            add(SvgDecoder.Factory())
        }
        .build()

    val request = ImageRequest.Builder(this.context)
        .crossfade(true)
        .crossfade(500)
        .data(url)
        .transformations(CircleCropTransformation())
        .target(this)
        .build()

    imageLoader.enqueue(request)
}