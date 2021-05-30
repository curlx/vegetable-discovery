package com.challenge.vegetablediscovery.ui.vegetablelist

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.challenge.vegetablediscovery.base.Event
import com.challenge.vegetablediscovery.domain.model.Issue
import com.challenge.vegetablediscovery.domain.model.Vegetable
import com.challenge.vegetablediscovery.domain.model.Vitamin
import com.challenge.vegetablediscovery.extension.toErrorMessage
import com.challenge.vegetablediscovery.repository.VegetableRepository
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class VegetableListViewModel(
    private val vegetableRepository: VegetableRepository,
    private val resources: Resources
) : ViewModel() {

    private val _selectedFilter: MutableLiveData<Vitamin> = MutableLiveData<Vitamin>(Vitamin.ALL)
    val selectedFilter: LiveData<Vitamin> = _selectedFilter

    private val _issueMessage: MutableLiveData<Event<String>> = MutableLiveData<Event<String>>()
    val issueMessage: LiveData<Event<String>> = _issueMessage

    private val _isRefreshing: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    private val _loadTrigger: MutableLiveData<Unit> = MutableLiveData(Unit)
    val vegetables: LiveData<List<Vegetable>> =
        _loadTrigger.switchMap {
            vegetableRepository.getVegetableList().map { getFilteredVegetable(it) }.asLiveData()
        }

    init {
        refreshVegetableCache()
    }

    fun refreshVegetableCache() {
        launchDataLoad {
            val issue = vegetableRepository.refreshVegetableCache()
            issue.toErrorMessage(resources)?.let {
                _issueMessage.value = Event(it)
            }
        }
    }

    fun setSelectedFilter(vitamin: Vitamin) {
        _selectedFilter.value = vitamin
        _loadTrigger.value = Unit
    }

    private fun getFilteredVegetable(allVegetables: List<Vegetable>): List<Vegetable> =
        allVegetables.filter {
            selectedFilter.value == Vitamin.ALL || selectedFilter.value == it.mainVitamin
        }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                block()
            } catch (e: Throwable) {
                FirebaseCrashlytics.getInstance().recordException(e)
                Issue.UNKNOWN.toErrorMessage(resources)?.let {
                    _issueMessage.value = Event(it)
                }
            } finally {
                _isRefreshing.value = false
            }
        }
    }
}