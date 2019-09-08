package com.outerspace.kurbandictionary

import com.outerspace.kurbandictionary.api.TermDefinition
import com.outerspace.kurbandictionary.api.TermDefinitionList
import com.outerspace.kurbandictionary.model.WebServiceCallback
import com.outerspace.kurbandictionary.model.WebServiceInterface
import com.outerspace.kurbandictionary.presenter.MainPresenter
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KUrbanDictionaryUnitTest {

    companion object {
        val HELLO : String = "Hello"
        val BYE : String = "BYE"
        val SUCCESS_RESPONSE : String = "Response to Hello"
        val ERROR_RESPONSE : String = "Error, not Hello"
    }

    /**
     * presenterTest - test for the MainPresenter method. It uses fakes to replace the implementation
     * for:
     *   - textConsumer and inProgress - whose original implementation have Android dependencies
     *   - WebService - This simpley chooses in between success and fail calling the appropriate
     *     callback function.
     *
     * The fakes is a good technique for Unit Test. It could also be implemented with Mockito
     */
    @Test
    fun presenterTest() {

        var testResult : String = ""

        val textConsumer : (String) -> Unit = {
            if(!testResult.isEmpty()) testResult = testResult.plus(" - ")
            testResult = testResult.plus(it.replace("\n", ""))
        }

        val inProgress : (Boolean) -> Unit = {
            if(!testResult.isEmpty()) testResult = testResult.plus(" - ")
            testResult = testResult.plus(if(it) "true" else "false")
        }

        var presenter : MainPresenter = MainPresenter(textConsumer, inProgress)
        presenter.webService = FakeWebService(presenter)

        testResult = ""
        presenter.fetchDefinitions(HELLO)
        assertTrue(testResult == "true - Response to Hello - false")

        testResult = ""
        presenter.fetchDefinitions(BYE)
        assertTrue(testResult == "true - Error, not Hello - false")
    }

    // The WebService Fake, it can be used since it is based on the WebServiceInterface.
    class FakeWebService(webServiceCallback : WebServiceCallback) : Callback<TermDefinitionList>, WebServiceInterface {
        val webServiceCallback : WebServiceCallback = webServiceCallback

        override fun fetchDefinitions(word: String) {
            if(word == KUrbanDictionaryUnitTest.HELLO)
                webServiceCallback.onSuccess(listOf(createTermDefinition(KUrbanDictionaryUnitTest.SUCCESS_RESPONSE)))
            else
                webServiceCallback.onFailure(KUrbanDictionaryUnitTest.ERROR_RESPONSE)
        }

        override fun onFailure(call: Call<TermDefinitionList>, t: Throwable) { }

        override fun onResponse(call: Call<TermDefinitionList>, response: Response<TermDefinitionList>) { }

        private fun createTermDefinition(definition: String) : TermDefinition {
            val termDefinition = TermDefinition()
            termDefinition.definition = definition
            return termDefinition
        }
    }

}

