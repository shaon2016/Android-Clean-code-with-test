package com.shaon2016.cleancodewithtest.view.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.shaon2016.cleancodewithtest.databinding.FragmentHomeBinding
import com.shaon2016.cleancodewithtest.view.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private val viewModel by viewModels<HomeViewModel>()

    override fun viewRelatedTask() {
        // TODO to test data load or not
        viewModel.loadSearchedProduct()

        viewModel.isLoading.observe(viewLifecycleOwner) {
            it?.let {
                if (it) progressBarHandler.show()
                else progressBarHandler.hide()
            }
        }
    }

}