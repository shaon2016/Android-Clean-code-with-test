package com.example.androidtestpractice.data.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.androidtestpractice.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class CartDaoTest {
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var context: Context
    private lateinit var db: AppDb
    private lateinit var dao: CartDao

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDb::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.cartDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun cartItemInsert_returnTrue() = runBlockingTest {
        val cart = Cart("ASD", 230.0, 2, 1)
        dao.insert(cart)

        val cartItems = dao.all().getOrAwaitValue()

        assertThat(cartItems).contains(cart)
    }

    @Test
    fun cartItemDelete_returnTrue() = runBlockingTest {
        val cart = Cart("ASD", 230.0, 2, 1)
        dao.insert(cart)
        dao.delete(cart)

        val cartItems = dao.all().getOrAwaitValue()

        assertThat(cartItems).doesNotContain(cart)
    }

    @Test
    fun cartTotalPrice_returnTrue() = runBlockingTest {
        val cart1 = Cart("ASD", 230.0, 2, 1)
        val cart2 = Cart("ASD", 100.0, 3, 2)
        dao.insert(cart1)
        dao.insert(cart2)

        val totalPrice = dao.totalPrice().getOrAwaitValue()

        assertThat(totalPrice).isEqualTo(230 * 2 + 100 * 3)
    }
}