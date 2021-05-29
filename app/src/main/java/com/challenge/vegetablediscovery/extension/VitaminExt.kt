package com.challenge.vegetablediscovery.extension

import androidx.annotation.ColorRes
import com.challenge.vegetablediscovery.R
import com.challenge.vegetablediscovery.domain.model.Vitamin

fun Vitamin.toNameAndSubGroupName(): Pair<String, String> =
    when (this) {
        Vitamin.A -> Pair("A", "")
        Vitamin.B1 -> Pair("B", "1")
        Vitamin.B2 -> Pair("B", "2")
        Vitamin.B3 -> Pair("B", "3")
        Vitamin.B5 -> Pair("B", "5")
        Vitamin.B6 -> Pair("B", "6")
        Vitamin.B7 -> Pair("B", "7")
        Vitamin.B9 -> Pair("B", "9")
        Vitamin.B12 -> Pair("B", "12")
        Vitamin.C -> Pair("C", "")
        Vitamin.D -> Pair("D", "")
        Vitamin.E -> Pair("E", "")
        Vitamin.K -> Pair("K", "")
        else -> Pair("?", "")
    }

@ColorRes
fun Vitamin.toBackgrounColor(): Int =
    when (this) {
        Vitamin.A -> R.color.yellow_500
        Vitamin.B1 -> R.color.amber_500
        Vitamin.B2 -> R.color.orange_500
        Vitamin.B3 -> R.color.deep_orange_500
        Vitamin.B5 -> R.color.red_500
        Vitamin.B6 -> R.color.pink_500
        Vitamin.B7 -> R.color.purple_500
        Vitamin.B9 -> R.color.blue_500
        Vitamin.B12 -> R.color.teal_500
        Vitamin.C -> R.color.cyan_500
        Vitamin.D -> R.color.lime_500
        Vitamin.E -> R.color.light_green_500
        Vitamin.K -> R.color.green_500
        else -> R.color.grey_500
    }
