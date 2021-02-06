package com.android.adhiyoz.ui.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.adhiyoz.ui.MainActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private val RC_SIGN_IN = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            // User is signed in
            navigateToMainActivity()
        } else {
            // No user is signed in
            firebaseAuthUi()
        }
    }

    private fun firebaseAuthUi() {
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.PhoneBuilder().build()
        )

        // Create and launch sign-in intent
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN
        )
    }

    private fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN && resultCode == Activity.RESULT_OK) {
            // Singin success
            navigateToMainActivity()
        } else {
            // Signin failed
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}