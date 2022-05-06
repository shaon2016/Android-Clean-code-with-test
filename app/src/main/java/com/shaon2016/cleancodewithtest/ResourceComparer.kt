package com.shaon2016.cleancodewithtest

import android.content.Context

class ResourceComparer {
    fun isEqualTo(context: Context, resId: Int, resStr: String) = context.getString(resId) == resStr
}