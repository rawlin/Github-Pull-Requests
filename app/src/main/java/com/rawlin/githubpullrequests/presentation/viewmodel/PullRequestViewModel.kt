package com.rawlin.githubpullrequests.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rawlin.githubpullrequests.domain.Event
import com.rawlin.githubpullrequests.domain.PullRepository
import com.rawlin.githubpullrequests.domain.Resource
import com.rawlin.githubpullrequests.domain.models.AllPullRequests
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PullRequestViewModel @Inject constructor(
    private val repository: PullRepository
): ViewModel() {

    private val _allPullRequests = MutableLiveData<Event<Resource<AllPullRequests>>>()
    val allPullRequests: LiveData<Event<Resource<AllPullRequests>>>
        get() = _allPullRequests

    fun fireNetworkCall() {
        viewModelScope.launch {
            _allPullRequests.value = Event(Resource.Loading())
            val requests = repository.getAllPullRequests()
            if (requests.data?.pullRequests?.isEmpty() == true) {
                _allPullRequests.value = Event(Resource.Error("No Pull Requests found"))
                return@launch
            }
            _allPullRequests.value = Event(repository.getAllPullRequests())
        }
    }

    fun forceRefresh() {
        _allPullRequests.value = _allPullRequests.value
    }

}
