package com.challenge.vegetablediscovery.extension

import com.challenge.vegetablediscovery.R
import com.challenge.vegetablediscovery.base.shouldEqual
import com.challenge.vegetablediscovery.domain.model.Vitamin
import org.junit.Test

class VitaminExtKtTest {

    @Test
    fun `should get vitamin name and subgroup name as expected`() {
        Vitamin.A.toNameAndSubGroupName() shouldEqual Pair("A", "")
        Vitamin.B1.toNameAndSubGroupName() shouldEqual Pair("B", "1")
        Vitamin.B2.toNameAndSubGroupName() shouldEqual Pair("B", "2")
        Vitamin.B3.toNameAndSubGroupName() shouldEqual Pair("B", "3")
        Vitamin.B5.toNameAndSubGroupName() shouldEqual Pair("B", "5")
        Vitamin.B6.toNameAndSubGroupName() shouldEqual Pair("B", "6")
        Vitamin.B7.toNameAndSubGroupName() shouldEqual Pair("B", "7")
        Vitamin.B9.toNameAndSubGroupName() shouldEqual Pair("B", "9")
        Vitamin.B12.toNameAndSubGroupName() shouldEqual Pair("B", "12")
        Vitamin.C.toNameAndSubGroupName() shouldEqual Pair("C", "")
        Vitamin.D.toNameAndSubGroupName() shouldEqual Pair("D", "")
        Vitamin.E.toNameAndSubGroupName() shouldEqual Pair("E", "")
        Vitamin.K.toNameAndSubGroupName() shouldEqual Pair("K", "")
        Vitamin.ALL.toNameAndSubGroupName() shouldEqual Pair("", "All")
        Vitamin.UNKNOWN.toNameAndSubGroupName() shouldEqual Pair("?", "")
    }

    @Test
    fun `should get vitamin background color as expected`() {
        Vitamin.A.toBackgrounColor() shouldEqual R.color.amber_500
        Vitamin.B1.toBackgrounColor() shouldEqual R.color.yellow_500
        Vitamin.B2.toBackgrounColor() shouldEqual R.color.orange_500
        Vitamin.B3.toBackgrounColor() shouldEqual R.color.deep_orange_500
        Vitamin.B5.toBackgrounColor() shouldEqual R.color.red_500
        Vitamin.B6.toBackgrounColor() shouldEqual R.color.pink_500
        Vitamin.B7.toBackgrounColor() shouldEqual R.color.purple_500
        Vitamin.B9.toBackgrounColor() shouldEqual R.color.blue_500
        Vitamin.B12.toBackgrounColor() shouldEqual R.color.teal_500
        Vitamin.C.toBackgrounColor() shouldEqual R.color.cyan_500
        Vitamin.D.toBackgrounColor() shouldEqual R.color.lime_500
        Vitamin.E.toBackgrounColor() shouldEqual R.color.light_green_500
        Vitamin.K.toBackgrounColor() shouldEqual R.color.green_500
        Vitamin.ALL.toBackgrounColor() shouldEqual R.color.deep_purple_500
        Vitamin.UNKNOWN.toBackgrounColor() shouldEqual R.color.grey_500
    }
}