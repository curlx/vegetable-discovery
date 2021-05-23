package com.challenge.vegetablediscovery.ui.vegetabledetail

import androidx.lifecycle.ViewModel

class VegetableDetailViewModel : ViewModel() {

    private val mockVegetableDetail = VegetableDetail(
            id = 1L,
            name = "Japanese Bunching Onion",
            description = """
                    One of the most ubiquitous of Japanese vegetables, negi, is often mistaken for the Welsh onion and the leek due to their striking similarities in appearance and taste.
                    
                    In Japanese, it may also be referred to as “naga negi” (long negi) or “shiro negi” (white negi).
                    
                    This long, green onion is used to add flavor to simmered dishes, as well as soups and hot pots.
                    
                    Negi is especially popular during the winter months, as it is also known for being a popular cold remedy.
                """.trimIndent(),
            imageUrl = "https://rimage.gnst.jp/livejapan.com/public/article/detail/a/00/01/a0001413/img/en/a0001413_parts_5995051ba9b48.jpg?20200717142223&q=80"
        )

    fun getVegetableDetail(vegetableId: Long): VegetableDetail = mockVegetableDetail
}