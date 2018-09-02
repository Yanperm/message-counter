/*
 * Copyright 2018 Midhun Harikumar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ae.apps.messagecounter.fragments

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ae.apps.messagecounter.R
import com.ae.apps.messagecounter.data.preferences.PreferenceRepository
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.SkuDetails
import kotlinx.android.synthetic.main.fragment_donations.*

class DonationsFragment : DonationsBaseFragment() {

    companion object {
        const val SKU_SMALL = "product_small"
        const val SKU_MEDIUM = "product_medium"
        const val SKU_LARGE = "product_large"

        fun newInstance() = DonationsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_donations, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()
    }

    override fun getSkus(): List<String> {
        return mutableListOf(SKU_SMALL, SKU_MEDIUM, SKU_LARGE)
    }

    override fun onBillingClientSetup() {
        loadingProgressBar?.visibility = View.GONE
    }

    override fun skuDetailsResponse(skuDetails: List<SkuDetails>) {
        /*Toast.makeText(requireContext(),
                "skuDetailsResponse size:" + skuDetails.size,
                Toast.LENGTH_SHORT)
                .show()*/
        skuDetails.forEach {
            when {
                it.sku == SKU_SMALL -> txtDonateSmallPrice.text = it.price
                it.sku == SKU_MEDIUM -> txtDonateMediumPrice.text = it.price
                it.sku == SKU_LARGE -> txtDonateLargePrice.text = it.price
            }
        }
    }

    override fun handlePurchase(purchase: Purchase) {
        Toast.makeText(requireContext(),
                "Thank You for your donation with order id" + purchase.orderId,
                Toast.LENGTH_SHORT)
                .show()
        val preferenceRepository = PreferenceRepository.newInstance(
                PreferenceManager.getDefaultSharedPreferences(requireContext()))
        preferenceRepository.saveDonationsMade()
    }

    override fun handlePurchaseError(purchases: List<Purchase>?) {
        Toast.makeText(requireContext(),
                "purchase error occurred",
                Toast.LENGTH_SHORT)
                .show()
    }

    override fun handleUserCancelled(purchases: List<Purchase>?) {

    }

    private fun initUI() {
        btnDonateSmall.setOnClickListener {
            launchBillingFlow(SKU_SMALL)
        }
        btnDonateMedium.setOnClickListener {
            launchBillingFlow(SKU_MEDIUM)
        }
        btnDonateLarge.setOnClickListener {
            launchBillingFlow(SKU_LARGE)
        }
    }
}