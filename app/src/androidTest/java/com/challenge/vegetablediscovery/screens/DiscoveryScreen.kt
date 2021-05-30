package com.challenge.vegetablediscovery.screens

import android.view.View
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.challenge.vegetablediscovery.R
import org.hamcrest.Matcher

class DiscoveryScreen: Screen<DiscoveryScreen>() {

    val vegetableList: KRecyclerView = KRecyclerView(
        builder = { withId(R.id.vegetable_list) },
        itemTypeBuilder = { itemType(::MainItem) }
    )

    class MainItem(parent: Matcher<View>) : KRecyclerItem<MainItem>(parent) {
        val image: KImageView = KImageView(parent) { withId(R.id.image) }
        val name: KTextView = KTextView(parent) { withId(R.id.name) }
        val mainVitamin: KTextView = KTextView(parent) {
            isDescendantOfA { withId(R.id.main_vitamin) }
            withId(R.id.text)
        }
    }
}