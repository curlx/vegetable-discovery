package com.challenge.vegetablediscovery.repository.mapper

interface Mapper<IN, OUT> {

    fun map(input: IN): OUT
}