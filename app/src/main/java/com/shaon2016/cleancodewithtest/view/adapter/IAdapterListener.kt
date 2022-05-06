package com.shaon2016.cleancodewithtest.view.adapter

import android.view.View
import android.view.ViewGroup
import com.shaon2016.cleancodewithtest.view.base.BaseViewHolder

interface IAdapterListener {
    fun <T> clickListener(position: Int, model: T, view: View)
    fun  getViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder
}