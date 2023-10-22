package com.example.sportsinteractiveassignment.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest

class NetworkManager(private val context: Context) {

    private lateinit var connectivityManager: ConnectivityManager
    private var isNetworkAvailable = false

    init {
        registerNetworkCallback()
    }

    fun isNetworkAvailable(): Boolean {
        return isNetworkAvailable
    }

    private fun registerNetworkCallback() {
        connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
            .build()

        connectivityManager.registerNetworkCallback(
            networkRequest,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    isNetworkAvailable = true
                }

                override fun onLost(network: Network) {
                    isNetworkAvailable = false
                }
            })
    }

}