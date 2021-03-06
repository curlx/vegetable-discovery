package com.challenge.vegetablediscovery.screens

import android.view.View
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.challenge.vegetablediscovery.R
import org.hamcrest.Matcher

class VegetableListScreen: Screen<VegetableListScreen>() {

    val vegetableList: KRecyclerView = KRecyclerView(
        builder = { withId(R.id.vegetable_list) },
        itemTypeBuilder = { itemType(::MainItem) }
    )
    val noResultView: KView = KView { withId(R.id.empty_vegetable_information) }
    val vitaminFilter: KRecyclerView = KRecyclerView(
        builder = { withId(R.id.vitamin_filter) },
        itemTypeBuilder = { itemType(::MainVitaminItem) }
    )

    class MainItem(parent: Matcher<View>) : KRecyclerItem<MainItem>(parent) {
        val image: KImageView = KImageView(parent) { withId(R.id.image) }
        val name: KTextView = KTextView(parent) { withId(R.id.name) }
        val mainVitamin: KTextView = KTextView(parent) {
            isDescendantOfA { withId(R.id.main_vitamin) }
            withId(R.id.text)
        }
    }

    class MainVitaminItem(parent: Matcher<View>) : KRecyclerItem<MainVitaminItem>(parent) {
        val vitamin: KView = KView(parent) { withId(R.id.vitamin) }
        val vitaminText: KTextView = KTextView(parent) {
            isDescendantOfA { withId(R.id.vitamin) }
            withId(R.id.text)
        }
    }
}