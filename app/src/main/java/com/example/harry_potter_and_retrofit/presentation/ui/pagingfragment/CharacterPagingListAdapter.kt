package com.example.harry_potter_and_retrofit.presentation.ui.pagingfragment

import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.harry_potter_and_retrofit.domain.model.CharacterPagingItem
import com.example.harry_potter_and_retrofit.presentation.ui.characterlistfragment.CharacterItemCompose

class CharacterPagingListAdapter :
    PagingDataAdapter<CharacterPagingItem, CharacterPagingListAdapter.PagingViewHolder>(Comparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        return PagingViewHolder(
            ComposeView(parent.context).apply {
                setContent {
                    CharacterItemCompose()
                }
            }

        )
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }

    }

    class PagingViewHolder(private val composeView: ComposeView) :
        RecyclerView.ViewHolder(composeView) {
        fun bind(character: CharacterPagingItem) {
            composeView.setContent {
                CharacterItemCompose(characterPagingItem = character)
            }
        }
    }

    class Comparator : DiffUtil.ItemCallback<CharacterPagingItem>() {
        override fun areItemsTheSame(
            oldItem: CharacterPagingItem,
            newItem: CharacterPagingItem,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CharacterPagingItem,
            newItem: CharacterPagingItem,
        ): Boolean {
            return oldItem == newItem
        }

    }
}