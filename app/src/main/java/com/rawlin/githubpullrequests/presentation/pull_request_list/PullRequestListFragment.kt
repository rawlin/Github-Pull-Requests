package com.rawlin.githubpullrequests.presentation.pull_request_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.motion.utils.ViewState
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.rawlin.githubpullrequests.R
import com.rawlin.githubpullrequests.databinding.FragmentPullRequestListBinding
import com.rawlin.githubpullrequests.domain.BindingFragment
import com.rawlin.githubpullrequests.domain.Resource
import com.rawlin.githubpullrequests.domain.isNetworkAvailable
import com.rawlin.githubpullrequests.domain.navigateSafely
import com.rawlin.githubpullrequests.presentation.pull_request_list.adapters.PRsAdapter
import com.rawlin.githubpullrequests.presentation.viewmodel.PullRequestViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.HttpException

@AndroidEntryPoint
class PullRequestListFragment : BindingFragment<FragmentPullRequestListBinding>() {

    private lateinit var adapter: PRsAdapter

    private val viewModel by viewModels<PullRequestViewModel>()

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentPullRequestListBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fireNetworkCall()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        setupListeners()

    }

    override fun onResume() {
        super.onResume()
        viewModel.forceRefresh()
    }

    private fun setupListeners() {
        adapter.setOnItemClickListener { pr, iv ->
            val extras = FragmentNavigatorExtras(iv to "two")
            val directions =
                PullRequestListFragmentDirections.actionPullRequestListFragmentToPullRequestDetailFragment(
                    pullRequest = pr,
                )
            findNavController().navigateSafely(directions, extras)
        }

        binding.apply {
            noInternetView.retryButton.setOnClickListener {
                viewModel.fireNetworkCall()
                noInternetView.root.isVisible = false
                toggleLoading(true)
            }

            errorView.retryButton.setOnClickListener {
                viewModel.fireNetworkCall()
                errorView.root.isVisible = false
                toggleLoading(true)
            }
        }
    }

    private fun setupObservers() {
        viewModel.allPullRequests.observe(viewLifecycleOwner) { prs ->
            when (prs.peekContent()) {
                is Resource.Error -> {
                    toggleLoading(false)
                    if (isNetworkAvailable(requireContext())) {
                        toggleErrorView(true)
                    } else {
                        toggleNoInternetView(true)
                    }
                }
                is Resource.Loading -> {
                    toggleLoading(true)
                }
                is Resource.Success -> {
                    toggleLoading(false)
                    prs.peekContent().data?.pullRequests?.let { adapter.submitList(it) }
                }
            }
        }
    }

    private fun toggleErrorView(toggle: Boolean) {
        binding.errorView.root.isVisible = toggle
    }

    private fun toggleNoInternetView(toggle: Boolean) {
        binding.noInternetView.root.isVisible = toggle
    }

    private fun toggleLoading(toggle: Boolean) {
        binding.progressBar.isVisible = toggle
    }

    private fun setupRecyclerView() {
        binding.apply {
            adapter = PRsAdapter()
            rvPrs.adapter = adapter
        }
    }
}
