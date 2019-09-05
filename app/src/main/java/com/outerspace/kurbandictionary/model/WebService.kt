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

object WebService : Callback<TermDefinitionList> {

    val BASE_URL = "https://mashape-community-urban-dictionary.p.rapidapi.com"

    private lateinit var api: UrbanDictionaryApi
    private lateinit var webServiceClient: WebServiceCallback

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

    public fun fetchDefinitions(word : String, webServiceClient : WebServiceCallback) {
        if (UrbanDictionaryApp.isNetworkConnected()) run {
            this.webServiceClient = webServiceClient
            val call = api.call(word)
            call.enqueue(this)
        }
    }

    override fun onFailure(call: Call<TermDefinitionList>, t: Throwable) {

    }

    override fun onResponse(call: Call<TermDefinitionList>, response: Response<TermDefinitionList>) {
        if(response.isSuccessful) {
            var list : List<TermDefinition> = response.body()?.list as List<TermDefinition>
            if(list != null)
                webServiceClient.onSuccess(list)
            return
        } else
            webServiceClient.onFailure(response.message())
    }

}