package com.shaon2016.cleancodewithtest

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat

import org.junit.Before
import org.junit.Test

class ResourceComparerTest {

    private lateinit var context: Context
    private lateinit var resourceComparer: ResourceComparer

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        resourceComparer = ResourceComparer()
    }

    @Test
    fun stringResourceSameAsGivenString_returnsTrue() {
        val result = resourceComparer.isEqualTo(context, R.string.app_name, "Android Test Practice")
        assertThat(result).isTrue()
    }

    @Test
    fun stringResourceNotSameAsGivenString_returnsFalse() {
        val result = resourceComparer.isEqualTo(context, R.string.app_name, "Hi")
        assertThat(result).isFalse()
    }
}