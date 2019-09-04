package com.outerspace.kurbandictionary

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

class UrbanDictionaryApp : Application() {

    companion object {
        private lateinit var instance : UrbanDictionaryApp

        fun isNetworkConnected() : Boolean {
            val manager: ConnectivityManager = instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = manager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
    }

    init {
        instance = this;
    }

}