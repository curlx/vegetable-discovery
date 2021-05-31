package com.challenge.vegetablediscovery.screens

import com.agoda.kakao.common.views.KSwipeView
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.challenge.vegetablediscovery.R

class VegetableDetailScreen: Screen<VegetableDetailScreen>() {

    val image: KImageView = KImageView { withId(R.id.image) }
    val name: KTextView = KTextView { withId(R.id.name) }
    val description: KTextView = KTextView { withId(R.id.description) }
    val closeButton: KImageView = KImageView { withId(R.id.close_button) }
    val container: KSwipeView = KSwipeView { withId(R.id.vegetable_detail_container) }
}