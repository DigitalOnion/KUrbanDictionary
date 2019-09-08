package com.outerspace.kurbandictionary.model

import com.google.gson.GsonBuilder
import com.outerspace.kurbandictionary.UrbanDictionaryApp
import com.outerspace.kurbandictionary.api.TermDefinition
import com.outerspace.kurbandictionary.api.TermDefinitionList
import com.outerspace.kurbandictionary.api.UrbanDictionaryApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WebService(webServiceCallback : WebServiceCallback) : Callback<TermDefinitionList>, WebServiceInterface {

    val BASE_URL = "https://mashape-community-urban-dictionary.p.rapidapi.com"

    private var webServiceCallback: WebServiceCallback = webServiceCallback
    private var api: UrbanDictionaryApi

    init {
        val gson = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        api = retrofit.create(UrbanDictionaryApi::class.java)
    }

    override fun fetchDefinitions(word : String) {
        if (UrbanDictionaryApp.isNetworkConnected()) run {
            val call = api.call(word)
            call.enqueue(this)
        }
    }

    override fun onFailure(call: Call<TermDefinitionList>, t: Throwable) {
        webServiceCallback.onFailure(t.message!!)
    }

    override fun onResponse(call: Call<TermDefinitionList>, response: Response<TermDefinitionList>) {
        if(response.isSuccessful) {
            var list : List<TermDefinition> = response.body()?.list as List<TermDefinition>
            if(list != null)
                webServiceCallback.onSuccess(list)
            return
        } else
            webServiceCallback.onFailure(response.message())
    }

}