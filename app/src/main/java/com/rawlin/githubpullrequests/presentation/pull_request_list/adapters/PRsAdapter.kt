package com.rawlin.githubpullrequests.presentation.pull_request_list.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rawlin.githubpullrequests.R
import com.rawlin.githubpullrequests.databinding.ItemPullRequestBinding
import com.rawlin.githubpullrequests.domain.models.PullRequest
import com.rawlin.githubpullrequests.domain.toDateString

class PRsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<PullRequest>() {

        override fun areItemsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
            return newItem == oldItem
        }

    }
    private val differ = AsyncListDiffer(this, differCallback)

    fun submitList(list: List<PullRequest>) {
        differ.submitList(list)
    }

    inner class PRViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemPullRequestBinding.bind(itemView)
        fun bind(item: PullRequest) = with(binding) {
            tvTitle.text = item.title
            tvUsername.text = item.user.login
            tvClosedAt.text = itemView.context.getString(R.string.closed_at, item.closedAt.toDateString())
            tvCreatedAt.text = itemView.context.getString(R.string.create_at, item.createdAt.toDateString())

            Glide.with(itemView)
                .load(item.user.avatarUrl)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView)

            binding.root.setOnClickListener {
                imageView.transitionName = itemView.context.getString(R.string.transition_image)
                onItemClickListener?.invoke(item, imageView)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return PRViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_pull_request,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PRViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((PullRequest, AppCompatImageView) -> Unit)? = null

    fun setOnItemClickListener(listener: (PullRequest, AppCompatImageView) -> Unit) {
        onItemClickListener = listener
    }

}

