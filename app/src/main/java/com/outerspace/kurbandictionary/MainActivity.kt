package com.outerspace.kurbandictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.outerspace.kurbandictionary.model.WebService
import com.outerspace.kurbandictionary.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main activity for the KUrbanDictionary - the Kotlin version of the Code Challenge
 * (there is another exercise written in Java, look at UrbanDictionary in the same repository)
 *
 * The app is structured as a MVP. it uses Retrofit2 as HTTP Client.
 */

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: MainPresenter;

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(result_text::setText, this::showProgress)
        presenter.webService = WebService(presenter)    // presenter implements WebServiceCallback
    }

    fun onClickQueryTerm(view : View) {
        presenter.fetchDefinitions(term_entry.text.toString())
    }

    fun showProgress(inProgress : Boolean) {
        progress.visibility = if(inProgress) View.VISIBLE else View.GONE
    }
}

