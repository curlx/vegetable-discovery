package com.challenge.vegetablediscovery.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.challenge.vegetablediscovery.MainActivity
import com.challenge.vegetablediscovery.CommonMockTestRule
import com.challenge.vegetablediscovery.api.model.response.VegetableResult
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
                    image { isVisible() }
                    name { isVisible(); hasText("Vegetable A") }
                    description { isVisible(); hasText("Vegetable A description") }
                    closeButton { isVisible(); click() }
                }

                childAt<VegetableListScreen.MainItem>(1) {
                    image { isVisible() }
                    name { isVisible(); hasText("Vegetable B") }
                    mainVitamin { isVisible(); hasText("C") }
                }
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