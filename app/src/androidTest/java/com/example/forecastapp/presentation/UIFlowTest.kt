package com.example.forecastapp.presentation

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.rule.ActivityTestRule
import com.example.forecastapp.presentation.weather.WeatherActivity
import org.junit.Rule
import android.support.test.rule.GrantPermissionRule

@RunWith(AndroidJUnit4::class)
class UIFlowTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<WeatherActivity>(WeatherActivity::class.java)

    @Rule
    @JvmField
    val permissionRule: GrantPermissionRule = GrantPermissionRule.grant(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION)

    @Test
    fun checkDataLoadProperly() {
        Extension.waitUntilViewMatched(withId(R.id.reportView), matches(isDisplayed()), 500)
        onView(withId(R.id.reportView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }
}
