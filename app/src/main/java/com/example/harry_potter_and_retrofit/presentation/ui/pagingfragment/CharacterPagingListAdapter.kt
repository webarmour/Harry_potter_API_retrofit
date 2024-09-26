package com.example.harry_potter_and_retrofit.presentation.ui.pagingfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.harry_potter_and_retrofit.R
import com.example.harry_potter_and_retrofit.databinding.PagingItemBinding
import com.example.harry_potter_and_retrofit.domain.model.CharacterPagingItem

class CharacterPagingListAdapter :
    PagingDataAdapter<CharacterPagingItem, CharacterPagingListAdapter.PagingViewHolder>(Comparator()) {


    class PagingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = PagingItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.paging_item, parent, false)
        return PagingViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val item = getItem(position)
        item?.imageUrl?.let {
            holder.binding.imCharacter.load(it)
        }
        item?.name?.let {
            holder.binding.tvCharacterName.text = it
        }
        item?.hogwartsHouse?.let {
            holder.binding.tvHogwartsHouse.text = it
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