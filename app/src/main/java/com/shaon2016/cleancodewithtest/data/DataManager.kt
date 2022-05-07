package com.shaon2016.cleancodewithtest.data

import com.shaon2016.cleancodewithtest.data.local.RoomHelper
import com.shaon2016.cleancodewithtest.data.remote.ApiHelper
import javax.inject.Inject

class DataManager @Inject constructor(
    val roomHelper: RoomHelper, val apiHelper: ApiHelper
)