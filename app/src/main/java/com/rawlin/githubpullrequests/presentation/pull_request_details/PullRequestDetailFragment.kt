package com.rawlin.githubpullrequests.presentation.pull_request_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.rawlin.githubpullrequests.databinding.FragmentPullRequestDetailBinding
import com.rawlin.githubpullrequests.domain.BindingFragment

class PullRequestDetailFragment : BindingFragment<FragmentPullRequestDetailBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentPullRequestDetailBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
