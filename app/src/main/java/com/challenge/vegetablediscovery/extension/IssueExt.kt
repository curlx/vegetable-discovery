package com.challenge.vegetablediscovery.extension

import android.content.res.Resources
import com.challenge.vegetablediscovery.R
import com.challenge.vegetablediscovery.domain.model.Issue

fun Issue.toErrorMessage(resources: Resources): String? =
    when (this) {
        Issue.NO_ISSUE -> null
        Issue.INTERNET_CONNECTION -> R.string.no_internet_connection
        Issue.UNKNOWN -> R.string.unknown_issue
    }?.let {
        resources.getString(it)
    }
