package com.rawlin.githubpullrequests.presentation.pull_request_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.rawlin.githubpullrequests.R
import com.rawlin.githubpullrequests.databinding.FragmentPullRequestDetailBinding
import com.rawlin.githubpullrequests.domain.BindingFragment
import com.rawlin.githubpullrequests.domain.toDateString

class PullRequestDetailFragment : BindingFragment<FragmentPullRequestDetailBinding>() {

    private val args: PullRequestDetailFragmentArgs by navArgs()

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentPullRequestDetailBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        populateView()

        binding.apply {
            ibBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun populateView() {
        binding.apply {
            context?.let {
                Glide.with(it)
                    .load(args.pullRequest.user.avatarUrl)
                    .into(ivProfile)
            }

            tvTitle.text = args.pullRequest.title
            tvDescription.text = args.pullRequest.body
            tvUser.text = context?.getString(R.string.by_, args.pullRequest.user.login)
            tvCreatedAt.text =
                context?.getString(R.string.create_at, args.pullRequest.createdAt.toDateString())
            tvClosedAt.text =
                context?.getString(R.string.closed_at, args.pullRequest.closedAt.toDateString())
        }
    }
}
