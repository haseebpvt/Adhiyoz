package com.android.adhiyoz.util

import android.app.Activity
import android.view.ViewGroup
import android.widget.Button
import com.android.adhiyoz.BuildConfig

/**
 * Add a button to the screen with a listener for testing purpose
 */
fun Activity.fakeButton(
    buttonText: String = "Test Button",
    show: Boolean = true,
    clicked: () -> Unit
) {
    if (show && BuildConfig.DEBUG) {
        val button = Button(this)
        button.text = buttonText
        button.setOnClickListener { clicked() }

        addContentView(
            button, ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
    }
}