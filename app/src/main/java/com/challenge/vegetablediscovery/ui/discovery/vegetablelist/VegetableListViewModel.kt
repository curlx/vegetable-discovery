package com.challenge.vegetablediscovery.ui.discovery.vegetablelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.challenge.vegetablediscovery.domain.Vegetable

class VegetableListViewModel : ViewModel() {

    private val _vegetables = MutableLiveData<List<Vegetable>>().apply {
        value = listOf(
            Vegetable(
                id = 1L,
                name = "Negi",
                description = """
                    One of the most ubiquitous of Japanese vegetables, negi, is often mistaken for the Welsh onion and the leek due to their striking similarities in appearance and taste.
                    
                    In Japanese, it may also be referred to as “naga negi” (long negi) or “shiro negi” (white negi).
                    
                    This long, green onion is used to add flavor to simmered dishes, as well as soups and hot pots.
                    
                    Negi is especially popular during the winter months, as it is also known for being a popular cold remedy.
                """.trimIndent(),
                imageUrl = "https://rimage.gnst.jp/livejapan.com/public/article/detail/a/00/01/a0001413/img/en/a0001413_parts_5995051ba9b48.jpg?20200717142223&q=80"
            ),
            Vegetable(
                id = 2L,
                name = "Kabocha",
                description = """
                    Kabocha is a type of squash, similar in taste to pumpkin, but known for being slightly sweeter. These gourds are stout and dark green in appearance, revealing a bright orange color when opened.
                    
                    Kabocha simmered in soy sauce, and sugar is a typical home-style side dish. When cooked, the skin is entirely edible and as delicious as the inside, so make sure not to waste it!

                    It is also a popular Japanese vegetable tempura item, and both diced and mashed kabocha are used for salads. This food is said to be rich in beta carotene and vitamin A, and also contains vitamin C, iron, and potassium.
                """.trimIndent(),
                imageUrl = "https://rimage.gnst.jp/livejapan.com/public/article/detail/a/00/01/a0001413/img/en/a0001413_parts_5995051ba9b48.jpg?20200717142223&q=80"
            ),
            Vegetable(
                id = 3L,
                name = "Kabocha",
                description = """
                    Kabocha is a type of squash, similar in taste to pumpkin, but known for being slightly sweeter. These gourds are stout and dark green in appearance, revealing a bright orange color when opened.
                    
                    Kabocha simmered in soy sauce, and sugar is a typical home-style side dish. When cooked, the skin is entirely edible and as delicious as the inside, so make sure not to waste it!

                    It is also a popular Japanese vegetable tempura item, and both diced and mashed kabocha are used for salads. This food is said to be rich in beta carotene and vitamin A, and also contains vitamin C, iron, and potassium.
                """.trimIndent(),
                imageUrl = "https://rimage.gnst.jp/livejapan.com/public/article/detail/a/00/01/a0001413/img/en/a0001413_parts_5995051ba9b48.jpg?20200717142223&q=80"
            ),
            Vegetable(
                id = 4L,
                name = "Kabocha",
                description = """
                    Kabocha is a type of squash, similar in taste to pumpkin, but known for being slightly sweeter. These gourds are stout and dark green in appearance, revealing a bright orange color when opened.
                    
                    Kabocha simmered in soy sauce, and sugar is a typical home-style side dish. When cooked, the skin is entirely edible and as delicious as the inside, so make sure not to waste it!

                    It is also a popular Japanese vegetable tempura item, and both diced and mashed kabocha are used for salads. This food is said to be rich in beta carotene and vitamin A, and also contains vitamin C, iron, and potassium.
                """.trimIndent(),
                imageUrl = "https://rimage.gnst.jp/livejapan.com/public/article/detail/a/00/01/a0001413/img/en/a0001413_parts_5995051ba9b48.jpg?20200717142223&q=80"
            )
        )
    }

    val vegetables: LiveData<List<Vegetable>> = _vegetables


}