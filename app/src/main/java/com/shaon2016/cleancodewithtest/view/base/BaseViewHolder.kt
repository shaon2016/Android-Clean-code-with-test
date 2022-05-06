package com.shaon2016.cleancodewithtest.view.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.shaon2016.cleancodewithtest.view.adapter.IAdapterListener

abstract class BaseViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun<T> onBind(position: Int, model:T, mCallback : IAdapterListener)

}