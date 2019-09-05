package com.outerspace.kurbandictionary.presenter

import com.outerspace.kurbandictionary.api.TermDefinition
import com.outerspace.kurbandictionary.model.WebService
import com.outerspace.kurbandictionary.model.WebServiceCallback

class MainPresenter : WebServiceCallback {
    private lateinit var stringConsumer : (String) -> (Unit)

    private lateinit var inProgress : (Boolean) -> (Unit)

    fun fetchDefinitions(term : String, stringConsumer: (String) -> (Unit), inProgress: (Boolean) -> (Unit)) {
        this.stringConsumer = stringConsumer
        this.inProgress = inProgress
        this.inProgress(true)
        WebService.fetchDefinitions(term, this)
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