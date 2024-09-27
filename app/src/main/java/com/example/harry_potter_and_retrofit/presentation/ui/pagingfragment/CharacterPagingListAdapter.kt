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
        fun bind(character: CharacterPagingItem){
            if (character.imageUrl != null) {
                binding.imCharacter.load(character.imageUrl)
            } else {
                binding.imCharacter.load(R.drawable.ic_face)
            }
            character.imageUrl.let {

            }
            binding.tvCharacterName.text = character.name
            binding.tvHogwartsHouse.text = character.hogwartsHouse

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.paging_item, parent, false)
        return PagingViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }

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