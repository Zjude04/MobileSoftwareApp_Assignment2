package com.example.msa_assignment2

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObjectNotFoundException
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private var device: UiDevice? = null

    @Before
    fun setUp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Launch the MainActivity by getting the package context
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = context.packageManager.getLaunchIntentForPackage("com.example.msa_assignment2")
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) // Clear any previous instances
            context.startActivity(intent)
        }
    }

    @Test
    @Throws(UiObjectNotFoundException::class)
    fun testStartActivityExplicitlyButton() {
        // Find and click the "Start Activity Explicitly" button
        val startButton =
            device!!.findObject(UiSelector().textContains("Start Activity Explicitly"))
        startButton.click()

        // Wait for SecondActivity to load and verify if it launched correctly
        val challengeItem = device!!.findObject(UiSelector().textContains("Challenge"))
        Assert.assertTrue("Second Activity not launched", challengeItem.exists())
    }
}