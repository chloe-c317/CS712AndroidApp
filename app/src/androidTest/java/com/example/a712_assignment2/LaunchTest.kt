package com.example.a712_assignment2

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.Before
import org.junit.Test
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Assert.assertTrue
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LaunchTest {
    private lateinit var device: UiDevice
    private val timeout = 5000L

    @Before
    fun setup(){
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressHome()

        val launcherPackage = device.launcherPackageName
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),timeout)
    }

    @Test
    fun launchTest(){
        val appIcon = device.findObject(By.text("712AndroidApp"))
        assertTrue("App icon not found", appIcon != null)
        appIcon.click()

        device.wait(Until.hasObject(By.text("Explicit button")),timeout)

        val startButton = device.findObject(By.text("Explicit button"))
        assertTrue("Explicit start button not found", startButton != null)
        startButton.click()

        device.wait(Until.hasObject(By.textContains("Device Fragmentation")),timeout)
        val challenge = device.findObject(By.textContains("Device Fragmentation"))
        assertTrue("Matching text not found", challenge != null)
    }

}