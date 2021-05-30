package com.challenge.vegetablediscovery.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.challenge.vegetablediscovery.MainActivity
import com.challenge.vegetablediscovery.CommonMockTestRule
import com.challenge.vegetablediscovery.api.model.response.VegetableResult
import com.challenge.vegetablediscovery.screens.VegetableListScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class VitaminFilterUiTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val commonMockTestRule = CommonMockTestRule(mockVegetableList = getVegetableList())

    @Test
    fun usersCanFilterVegetableByVitamin() {
        onScreen<VegetableListScreen> {
            vegetableList {
                isVisible()
                hasSize(1)

                childAt<VegetableListScreen.MainItem>(0) {
                    image { isVisible() }
                    name { isVisible(); hasText("Vegetable A") }
                    mainVitamin { isVisible(); hasText("K") }
                }
            }
            noResultView { isGone() }

            vitaminFilter {
                isVisible()
                swipeLeft() // this test could be flaky because swipe has a different effect on different devices
                Thread.sleep(3_000) // workaround - it should wait UI before proceed a next step, without Idling resource
            }
            vegetableList { isGone(); hasSize(0) }
            noResultView { isVisible() }

            vitaminFilter {
                swipeRight()
                Thread.sleep(3_000)
            }
            vegetableList { isVisible(); hasSize(1) }
            noResultView { isGone() }
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
            )
        )
    }
}