package com.example.forecastapp.presentation

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.ViewInteraction
import android.view.View
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import java.lang.Thread.sleep

class Extension {
    companion object {
        fun waitUntilViewMatched(viewMatcher: Matcher<View>, assertion: ViewAssertion, durationInMillis: Long) {
            val startTime = System.currentTimeMillis()
            while (true) {
                var vi: ViewInteraction? = null
                try {
                    vi = onView(viewMatcher)
                } catch (e: Throwable) {
                    //Do nothing
                }

                if (vi != null) {
                    try {
                        vi.check(assertion)
                        break
                    } catch (e: Throwable) {
                        //Do nothing
                    }

                }
                val diff = System.currentTimeMillis() - startTime
                if (diff > durationInMillis) {
                    assertThat<Any>("waitUntilViewMatched() Timeout reached (Aborted)",
                            true, Matchers.not(Matchers.anything()))
                }
                sleep(100)
            }
        }
    }
}