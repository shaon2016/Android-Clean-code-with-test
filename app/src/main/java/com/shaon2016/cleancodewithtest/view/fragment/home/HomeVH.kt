package com.shaon2016.cleancodewithtest.view.fragment.home

import com.shaon2016.cleancodewithtest.data.model.Product
import com.shaon2016.cleancodewithtest.databinding.RvProductListItemBinding
import com.shaon2016.cleancodewithtest.view.adapter.IAdapterListener
import com.shaon2016.cleancodewithtest.view.base.BaseViewHolder

class HomeVH(private val view : RvProductListItemBinding) : BaseViewHolder(view.root) {
    override fun <T> onBind(position: Int, model: T, mCallback: IAdapterListener) {
        model as Product

        view.tvTitle.text = model.title

        view.tvPrice.text = "Price ${model.price}"
    }
}