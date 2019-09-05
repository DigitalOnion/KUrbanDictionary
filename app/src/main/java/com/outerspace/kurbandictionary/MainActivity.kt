package com.outerspace.kurbandictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.outerspace.kurbandictionary.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main activity for the KUrbanDictionary - the Kotlin version of the Code Challenge
 * (there is another exercise written in Java, look at UrbanDictionary in the same repository)
 *
 * The app is structured as a MVP. it uses Retrofit2 as HTTP Client.
 */

class MainActivity : AppCompatActivity() {

    private val presenter: MainPresenter = MainPresenter();

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickQueryTerm(view : View) {
        presenter.fetchDefinitions(term_entry.text.toString(), result_text::setText, this::showProgress )
    }

    fun showProgress(inProgress : Boolean) {
        progress.visibility = if(inProgress) View.VISIBLE else View.GONE
    }
}
