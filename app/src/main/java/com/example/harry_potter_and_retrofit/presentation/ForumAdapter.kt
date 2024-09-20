package com.example.harry_potter_and_retrofit.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.harry_potter_and_retrofit.R
import com.example.harry_potter_and_retrofit.databinding.ForumItemBinding
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class ForumAdapter(
    private val options: FirebaseRecyclerOptions<ForumViewModel.ForumItem>,
) : FirebaseRecyclerAdapter<ForumViewModel.ForumItem, ForumAdapter.ForumViewHolder>(options) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumViewHolder {
        return ForumViewHolder(
            ForumItemBinding.bind(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.forum_item, parent, false)
                )
        )

    }

    override fun onBindViewHolder(
        holder: ForumViewHolder,
        position: Int,
        model: ForumViewModel.ForumItem,
    ) {
        holder.bind(model)
    }

    inner class ForumViewHolder(
        private val binding: ForumItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(forumItem: ForumViewModel.ForumItem) {
            binding.tvName.text = forumItem.user ?: ANONYMOUS
            binding.tvMessage.text = forumItem.text
        }
    }

    companion object {
        const val ANONYMOUS = "Anonymous"
    }
}