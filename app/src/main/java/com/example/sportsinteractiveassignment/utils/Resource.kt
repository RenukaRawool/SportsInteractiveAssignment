package com.example.sportsinteractiveassignment.utils

sealed class Resource<out T> {

    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val errorMessage: String) : Resource<Nothing>()
    object Unknown :
        Resource<Nothing>() //can be extended in case you want to send the Throwable or custom message

    object NoInternet : Resource<Nothing>()
}
