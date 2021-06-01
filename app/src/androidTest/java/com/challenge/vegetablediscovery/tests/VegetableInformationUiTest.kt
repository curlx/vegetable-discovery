package com.challenge.vegetablediscovery.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.challenge.vegetablediscovery.MainActivity
import com.challenge.vegetablediscovery.CommonMockTestRule
import com.challenge.vegetablediscovery.api.model.response.VegetableResult
import com.challenge.vegetablediscovery.extensions.isVisibleWithin
import com.challenge.vegetablediscovery.screens.VegetableListScreen
import com.challenge.vegetablediscovery.screens.VegetableDetailScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class VegetableInformationUiTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val commonMockTestRule = CommonMockTestRule(mockVegetableList = getVegetableList())

    // BottomSheetDialog show/dismiss are flaky with Espresso, workaround with waitUtil approach
    @Test
    fun usersCanSeeVegetableListAndCheckVegetableDetail() {
        onScreen<VegetableListScreen> {
            vegetableList {
                isVisible()
                hasSize(2)

                childAt<VegetableListScreen.MainItem>(0) {
                    image { isVisible() }
                    name { isVisible(); hasText("Vegetable A") }
                    mainVitamin { isVisible(); hasText("K") }
                    click()
                }
                onScreen<VegetableDetailScreen> {
                    container { isVisibleWithin(1000L) }
                    image { isVisible() }
                    name { isVisible(); hasText("Vegetable A") }
                    description { isVisible(); hasText("Vegetable A description") }
                    closeButton { isVisible(); click() }
                }
                isVisibleWithin(1000L)

                childAt<VegetableListScreen.MainItem>(1) {
                    image { isVisible() }
                    name { isVisible(); hasText("Vegetable B") }
                    mainVitamin { isVisible(); hasText("C") }
                    doubleClick() // intended to do double click to check and check if it shows only detail screen
                }
                onScreen<VegetableDetailScreen> {
                    container { isVisibleWithin(1000L) }
                    image { isVisible() }
                    name { isVisible(); hasText("Vegetable B") }
                    description { isVisible(); hasText("Vegetable B description") }
                    container { swipeDown() } // another way to close the detail screen
                }
                isVisibleWithin(1000L) // should see the list screen instead of another detail screen after double click
            }
        }
    }

    private fun getVegetableList(): List<VegetableResult> {
        return listOf(
            VegetableResult(
                id = 1L,
                name = "Vegetable A",
                imageUrl = "https://rimage.gnst.jp/livejapan.com/public/article/detail/a/00/01/a0001413/img/en/a0001413_parts_5995051ba9b48" +
                    ".jpg?20200717142223&q=80",
                mainVitamin = "K",
                description = "Vegetable A description"
            ),
            VegetableResult(
                id = 2L,
                name = "Vegetable B",
                imageUrl = "https://rimage.gnst.jp/livejapan.com/public/article/detail/a/00/01/a0001413/img/en/a0001413_parts_5995051f8a209" +
                    ".jpg?20200717142223&q=80",
                mainVitamin = "C",
                description = "Vegetable B description"
            )
        )
    }
}