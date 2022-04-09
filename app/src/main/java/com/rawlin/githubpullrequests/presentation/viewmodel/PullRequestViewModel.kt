package com.rawlin.githubpullrequests.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rawlin.githubpullrequests.data.PullRepositoryImpl
import com.rawlin.githubpullrequests.domain.Event
import com.rawlin.githubpullrequests.domain.PullRepository
import com.rawlin.githubpullrequests.domain.Resource
import com.rawlin.githubpullrequests.domain.models.AllPullRequests
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PullRequestViewModel @Inject constructor(
    private val repository: PullRepositoryImpl
): ViewModel() {

    private val _allPullRequests = MutableLiveData<Event<Resource<AllPullRequests>>>()
    val allPullRequests: LiveData<Event<Resource<AllPullRequests>>>
        get() = _allPullRequests

    fun fireNetworkCall() {
        viewModelScope.launch {
            _allPullRequests.value = Event(Resource.Loading())
            _allPullRequests.value = Event(repository.getAllPullRequests())
        }
    }

}
