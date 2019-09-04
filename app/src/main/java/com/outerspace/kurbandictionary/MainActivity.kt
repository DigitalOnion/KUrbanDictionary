package com.outerspace.kurbandictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.outerspace.kurbandictionary.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

public class MainActivity : AppCompatActivity() {

    private val presenter: MainPresenter = MainPresenter();

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public fun onClickQueryTerm(view : View) {
        presenter.fetchDefinitions(term_entry.text.toString(), result_text::setText)
    }
}
