package com.challenge.vegetablediscovery.ui.vitaminfilter

import com.challenge.vegetablediscovery.base.shouldEqual
import com.challenge.vegetablediscovery.domain.model.Vitamin
import org.junit.Before
import org.junit.Test

class VitaminFilterViewModelTest {

    private lateinit var sut: VitaminFilterViewModel

    @Before
    fun setup() {
        sut = VitaminFilterViewModel()
    }

    @Test
    fun `vitamin filters should not include unknown vitamin type`() {
        sut.vitaminFilters.contains(Vitamin.UNKNOWN) shouldEqual false
    }

    @Test
    fun `vitamin filters should start with all vitamin type`() {
        sut.vitaminFilters[0] shouldEqual Vitamin.ALL
    }
}