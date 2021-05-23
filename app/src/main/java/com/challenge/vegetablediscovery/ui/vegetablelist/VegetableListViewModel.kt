package com.challenge.vegetablediscovery.ui.vegetablelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.challenge.vegetablediscovery.domain.Vegetable

class VegetableListViewModel : ViewModel() {

    private val _vegetables = MutableLiveData<List<Vegetable>>().apply {
        value = listOf(
            Vegetable(
                id = 1L,
                name = "Japanese Bunching Onion",
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
                name = "Kabocha Pumpkin",
                description = """
                    Kabocha is a type of squash, similar in taste to pumpkin, but known for being slightly sweeter. These gourds are stout and dark green in appearance, revealing a bright orange color when opened.
                    
                    Kabocha simmered in soy sauce, and sugar is a typical home-style side dish. When cooked, the skin is entirely edible and as delicious as the inside, so make sure not to waste it!

                    It is also a popular Japanese vegetable tempura item, and both diced and mashed kabocha are used for salads. This food is said to be rich in beta carotene and vitamin A, and also contains vitamin C, iron, and potassium.
                """.trimIndent(),
                imageUrl = "https://rimage.gnst.jp/livejapan.com/public/article/detail/a/00/01/a0001413/img/en/a0001413_parts_5995051f8a209.jpg?20200717142223&q=80"
            ),
            Vegetable(
                id = 3L,
                name = "Mooli",
                description = """
                    The word daikon means “big root,” and this white radish certainly lives up to its name!

                    Crispy when raw, and tender when cooked, this Japanese root vegetable has a milder taste than some other types of radishes.

                    This versatile Japanese vegetable can be prepared in a variety of ways. It may be simmered with seafood, cooked in soups or pot dishes such as oden. When grated raw, it is referred to as daikon oroshi, which is popularly eaten with grilled fish, both soba and udon noodles, and also tempura.

                    Daikon is known for being a good source of fiber, folate, riboflavin, and potassium, as well as containing various other vitamins.
                """.trimIndent(),
                imageUrl = "https://rimage.gnst.jp/livejapan.com/public/article/detail/a/00/01/a0001413/img/en/a0001413_parts_599505241045b.jpg?20200717142223&q=80"
            ),
            Vegetable(
                id = 4L,
                name = "Perilla",
                description = """
                    If you’re looking for an herb that leaves an impact on your tastebuds, look no further than shiso.

                    Known in the west as perilla or beefsteak plant, this leaf is known for packing a sharp, flavorful punch.

                    There are different types of shiso used in Japan. Green leaves are known as aojiso, while the red leaves are akajiso.

                    In Japan, aojiso is a characteristic flavor for Japanese-style salad dressing, and shiso can often be found paired with umeboshi (pickled plums) when adding flavor to rice.

                    In addition to being flavorful, this leaf is known for its nutritional benefits, including its relatively high omega fatty acid content.
                """.trimIndent(),
                imageUrl = "https://rimage.gnst.jp/livejapan.com/public/article/detail/a/00/01/a0001413/img/en/a0001413_parts_59950528e9e90.jpg?20200717142223&q=80"
            ),
            Vegetable(
                id = 5L,
                name = "Japanese Mountain Yam",
                description = """
                    Naga-imo means “long potato,” and it is easy to see why! However, unlike a typical potato, it is most commonly served grated, a preparation known as tororo, which is a slimy, gooey topping served on rice and noodles.

                    It is also used as a thickener in the pancake-like dish called okonomiyaki.
                    
                    Its mellow taste makes it the perfect accompaniment as a vegetable whose flavor will not overpower the main dish.
                    
                    Even better, it is a good source of protein, potassium, magnesium, and other vitamins!
                """.trimIndent(),
                imageUrl = "https://rimage.gnst.jp/livejapan.com/public/article/detail/a/00/01/a0001413/img/en/a0001413_parts_5995054355ce4.jpg?20200717142223&q=80"
            )
        )
    }

    val vegetables: LiveData<List<Vegetable>> = _vegetables


}