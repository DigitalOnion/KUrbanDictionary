package com.outerspace.kurbandictionary.model

import com.outerspace.kurbandictionary.api.TermDefinition

interface WebServiceCallback {
    fun onSuccess(termDefinitions : List<TermDefinition>)

    fun onFailure(message : String);
}
