package com.challenge.vegetablediscovery.screens

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.challenge.vegetablediscovery.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DiscoveryUiTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun usersCanSeeVegetableListAndCheckVegetableDetail() {
        onScreen<DiscoveryScreen> {
            vegetableList {
                isVisible()
                hasSize(3)

                childAt<DiscoveryScreen.MainItem>(0) {
                    image { isVisible() }
                    name { isVisible(); hasText("Japanese Bunching Onion") }
                    click()
                }

                onScreen<VegetableDetailScreen> {
                    image { isVisible() }
                    name { isVisible(); hasText("Japanese Bunching Onion") }
                    description { isVisible(); containsText("One of the most ubiquitous of Japanese vegetables, negi") }
                    closeButton { isVisible(); click() }
                }

                childAt<DiscoveryScreen.MainItem>(1) {
                    image { isVisible() }
                    name { isVisible(); hasText("Kabocha Pumpkin") }
                }
            }
        }
    }
}