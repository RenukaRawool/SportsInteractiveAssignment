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

val countryMap = mapOf("IND" to "https://hatscripts.github.io/circle-flags/flags/in.svg",
    "NZ" to "https://hatscripts.github.io/circle-flags/flags/nz.svg",
    "PAK" to "https://hatscripts.github.io/circle-flags/flags/pk.svg",
    "SA" to "https://hatscripts.github.io/circle-flags/flags/za.svg")

fun ImageView.setImage(url:String){
    val imageLoader = ImageLoader.Builder(this.context)
        .components{
            add(SvgDecoder.Factory())
        }
        .build()

    val request = ImageRequest.Builder(this.context)
        .data(url)
        .target(this)
        .build()

    imageLoader.enqueue(request)
}