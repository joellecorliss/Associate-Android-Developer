package com.example.tipcalculator

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString


@RunWith(AndroidJUnit4::class)
class CalculatorTests {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_20_percent_tip() {
        /* 1. Test EditText element with input */
        onView(withId(R.id.new_cost_of_service)).perform(typeText("50.00")).perform(ViewActions.closeSoftKeyboard())
        /* 2. Test click Button element to calculate */
        onView(withId(R.id.calculate_button)).perform(click())
        /* 3. Test check if tip amount matches $10 - because 20% of $50 is $10 */
        onView(withId(R.id.tip_result)).check(matches(withText(containsString("$10.00"))))
    }

    @Test
    fun calculate_15_percent_tip_roundup() {
        onView(withId(R.id.new_cost_of_service)).perform(typeText("15"))
        onView(withId(R.id.option_fifteen_percent)).perform(click())
        onView(withId(R.id.round_up_switch)).perform(click())
        onView(withId(R.id.calculate_button)).perform(click())
        onView(withId(R.id.tip_result)).check(matches(withText(containsString("$2"))))
    }
}

