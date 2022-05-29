package com.shaon2016.cleancodewithtest.view.activity.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.shaon2016.cleancodewithtest.R
import com.shaon2016.cleancodewithtest.data.network.Helper.setResponse
import com.shaon2016.cleancodewithtest.repo.home.HomeRepoImpl
import com.shaon2016.cleancodewithtest.view.activity.BaseActivityTest
import com.shaon2016.cleancodewithtest.view.base.BaseViewHolder
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@LargeTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class MainActivityTest : BaseActivityTest() {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Inject
    lateinit var homeRepoImpl: HomeRepoImpl

    @Test
    fun listGoesOverTheFold() {
        onView(withText("Hello world!")).check(matches(isDisplayed()))
    }

    @Test
    fun insertTextIntoTextSearchField() {
        onView(withId(R.id.evSearch)).perform(typeText("notebook"))
            .check(matches(withText("notebook")))
    }

    @Test
    fun performSearchAndCheckDataInRecycler() {
        runTest {
            mockWebServer.setResponse("product_search_success_response2.json", 200)

            onView(withId(R.id.evSearch)).perform(typeText("notebook"))
            onView(withId(R.id.ivSearch)).perform(click())

            onView(withId(R.id.rList)).perform(RecyclerViewActions.scrollToLastPosition<BaseViewHolder>())
            onView(withText("Notebook Acer Aspire 5 A515-43 Silver 15.6 , Amd Ryzen 3 3200u 4gb De Ram 128gb Ssd, Amd Radeon Vega 3 1920x1080px Windows 10 Home")).check(
                matches(isDisplayed()))

        }
    }


}