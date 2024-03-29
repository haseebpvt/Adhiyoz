package com.android.adhiyoz.ui.payment.gpay

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.android.adhiyoz.R
import com.android.adhiyoz.util.isAppInstalled
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class GooglePayActivity : AppCompatActivity() {

    private val viewModel: GooglePayViewModel by viewModels()

    companion object {
        const val GOOGLE_PAY_PACKAGE = "com.google.android.apps.nbu.paisa.user"
        const val KEY_AMOUNT = "amount"
        const val KEY_PRODUCT_ID = "product_id"
        private const val GOOGLE_PAY_RC = 100
        private const val NAME = "Haseeb Pavaratty"
        private const val UPI_ID = "haseebpvt@okicici"
        private const val TRANSACTION_NOTE = "Adhiyoz Payment"
    }

    private var productId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_pay)

        val amount = intent.extras?.getString(KEY_AMOUNT) ?: "0.00"
        productId = intent.extras?.getString(KEY_PRODUCT_ID)

        val paymentUri = getPaymentUri(
            upiId = UPI_ID,
            name = NAME,
            transactionNote = TRANSACTION_NOTE,
            amount = amount
        )

        if (isAppInstalled(GOOGLE_PAY_PACKAGE)) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = paymentUri
            intent.setPackage(GOOGLE_PAY_PACKAGE)
            startActivityForResult(intent, GOOGLE_PAY_RC)
        }
    }

    private fun getPaymentUri(
        upiId: String,
        name: String,
        transactionNote: String,
        amount: String,
    ): Uri {
        return Uri.Builder()
            .scheme("upi")
            .authority("pay")
            .appendQueryParameter("pa", upiId)
            .appendQueryParameter("pn", name)
            .appendQueryParameter("tn", transactionNote)
            .appendQueryParameter("am", amount)
            .appendQueryParameter("cu", "INR")
            .build()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val status = data?.getStringExtra("Status")?.toLowerCase(Locale.ROOT) ?: return

        if (resultCode == Activity.RESULT_OK && status == "success") {
            val firebaseUserId = FirebaseAuth.getInstance().currentUser?.uid
            val productId = productId

            if (firebaseUserId != null && productId != null) {
                viewModel.placeOrder(firebaseUserId, productId)
                Toast.makeText(this, "Order placed", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Order Failed", Toast.LENGTH_LONG).show()
            }


        } else {
            Toast.makeText(this, "Payment Failed", Toast.LENGTH_LONG).show()
        }
    }
}