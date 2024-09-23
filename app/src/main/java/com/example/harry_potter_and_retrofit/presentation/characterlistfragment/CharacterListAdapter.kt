package com.example.harry_potter_and_retrofit.presentation.characterlistfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.harry_potter_and_retrofit.R
import com.example.harry_potter_and_retrofit.databinding.CharacterItemBinding
import com.example.harry_potter_and_retrofit.domain.model.CharacterItem

class CharacterListAdapter :
    ListAdapter<CharacterItem, CharacterListAdapter.ViewHolder>(Comparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.character_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.imCharacter.load(item.image)
        holder.binding.tvCharacterName.text = item.character
        holder.binding.tvHogwartsHouse.text = item.hogwartsHouse
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CharacterItemBinding.bind(view)

    }


    class Comparator : DiffUtil.ItemCallback<CharacterItem>() {
        override fun areItemsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
            return oldItem == newItem
        }

    }


}