package com.rawlin.githubpullrequests.presentation.pull_request_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.rawlin.githubpullrequests.R
import com.rawlin.githubpullrequests.databinding.FragmentPullRequestListBinding
import com.rawlin.githubpullrequests.domain.BindingFragment
import com.rawlin.githubpullrequests.domain.Resource
import com.rawlin.githubpullrequests.presentation.pull_request_list.adapters.PRsAdapter
import com.rawlin.githubpullrequests.presentation.viewmodel.PullRequestViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PullRequestListFragment : BindingFragment<FragmentPullRequestListBinding>() {

    private val adapter = PRsAdapter()

    private val viewModel by viewModels<PullRequestViewModel>()

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentPullRequestListBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()

        viewModel.fireNetworkCall()
    }

    private fun setupObservers() {
        viewModel.allPullRequests.observe(viewLifecycleOwner) { prs ->
            when (prs.getContentIfNotHandled()) {
                is Resource.Error -> {
                    toggleLoading(false)
                }
                is Resource.Loading -> {toggleLoading(true)}
                is Resource.Success -> {
                    toggleLoading(false)
                    prs.peekContent().data?.pullRequests?.let { adapter.submitList(it) }
                }
                null -> {
                    toggleLoading(false)
                }
            }
        }
    }

    private fun toggleLoading(toggle: Boolean) {
        binding.progressBar.isVisible = toggle
    }

    private fun setupRecyclerView() {
        binding.apply {
            rvPrs.adapter = adapter
        }
    }
}