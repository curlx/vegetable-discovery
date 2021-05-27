package com.challenge.vegetablediscovery.base

import org.mockito.kotlin.whenever

infix fun Any?.returns(mockValue: Any?) = whenever(this).thenReturn(mockValue)
