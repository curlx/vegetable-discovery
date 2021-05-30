package com.challenge.vegetablediscovery.base

import com.google.common.truth.Truth.assertThat

infix fun <T> T.shouldEqual(expected: T?): T = this.apply { assertThat(this).isEqualTo(expected) }
infix fun <T> T.shouldNotEqual(expected: T?): T = this.apply { assertThat(this).isNotEqualTo(expected) }