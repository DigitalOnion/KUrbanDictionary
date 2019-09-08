package com.outerspace.kurbandictionary.presenter

import com.outerspace.kurbandictionary.api.TermDefinition
import com.outerspace.kurbandictionary.model.WebService
import com.outerspace.kurbandictionary.model.WebServiceCallback
import com.outerspace.kurbandictionary.model.WebServiceInterface

class MainPresenter(stringConsumer : (String) -> (Unit), inProgress: (Boolean) -> (Unit))
    : WebServiceCallback {
    private val stringConsumer : (String) -> (Unit) = stringConsumer
    private val inProgress : (Boolean) -> (Unit) = inProgress

    lateinit var webService : WebServiceInterface

    fun fetchDefinitions(term : String) {
        this.inProgress(true)
        webService.fetchDefinitions(term)
    }

    override fun onSuccess(termDefinitions: List<TermDefinition>) {
        val sb = StringBuilder()
        for (definition in termDefinitions) {
            sb.append(definition.definition).append('\n')
        }
        stringConsumer(sb.toString())
        inProgress(false)
    }

    override fun onFailure(message: String) {
        stringConsumer(message)
        inProgress(false)
    }
}