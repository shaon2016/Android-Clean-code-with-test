package com.shaon2016.cleancodewithtest.view.fragment.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaon2016.cleancodewithtest.data.model.Product
import com.shaon2016.cleancodewithtest.databinding.FragmentHomeBinding
import com.shaon2016.cleancodewithtest.databinding.RvProductListItemBinding
import com.shaon2016.cleancodewithtest.view.adapter.IAdapterListener
import com.shaon2016.cleancodewithtest.view.base.BaseFragment
import com.shaon2016.cleancodewithtest.view.base.BaseRecyclerAdapter
import com.shaon2016.cleancodewithtest.view.base.BaseViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private val viewModel by viewModels<HomeViewModel>()

    private val adapter by lazy {
        BaseRecyclerAdapter(requireContext(), object : IAdapterListener {
            override fun <T> clickListener(position: Int, model: T, view: View) {
                model as Product

            }

            override fun getViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
                return HomeVH(
                    RvProductListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }, ArrayList<Product>())
    }

    override fun viewRelatedTask() {
        // TODO to test data load or not
        viewModel.loadSearchedProduct()

        viewModel.isLoading.observe(viewLifecycleOwner) {
            it?.let {
                if (it) progressBarHandler.show()
                else progressBarHandler.hide()
            }
        }
        viewModel.products.observe(viewLifecycleOwner) {
            it?.let {
                adapter.addData(it as ArrayList<Product>)
            }
        }

        initRv()
    }

    private fun initRv() {
        binding.rList.layoutManager = LinearLayoutManager(requireContext())
        binding.rList.adapter = adapter
    }

}