package com.android.adhiyoz.ui.checkout

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.android.adhiyoz.R
import com.android.adhiyoz.constant.PaymentMethods
import com.android.adhiyoz.databinding.FragmentCheckoutBinding
import com.android.adhiyoz.result.EventObserver
import com.android.adhiyoz.ui.payment.gpay.GooglePayActivity
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_checkout_delivery_method.view.*

@AndroidEntryPoint
class CheckoutFragment : Fragment() {

    private val viewModel: CheckoutViewModel by viewModels()

    private val args: CheckoutFragmentArgs by navArgs()

    private lateinit var binding: FragmentCheckoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckoutBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
            lifecycleOwner = this@CheckoutFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadProductDetails(productId = args.productId)

        FirebaseAuth.getInstance().currentUser?.uid?.let {
            viewModel.loadCustomerDetails(userId = it)
        }

        binding.paymentMethodLayout.payment_radio_group
            .setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.cod_button -> viewModel.setPaymentMethod(PaymentMethods.COD)
                    R.id.google_pay_button -> viewModel.setPaymentMethod(PaymentMethods.GOOGLE_PAY)
                }
            }

        viewModel.actionPlaceOrder.observe(viewLifecycleOwner, EventObserver {
            when (it) {
                PaymentMethods.GOOGLE_PAY -> {
                    requireActivity().run {
                        val amount = viewModel.product.value?.msrp

                        startActivity(Intent(this, GooglePayActivity::class.java).apply {
                            putExtra(GooglePayActivity.KEY_AMOUNT, amount.toString())
                        })
                    }
                }
                PaymentMethods.COD -> {

                }
            }
        })
    }
}