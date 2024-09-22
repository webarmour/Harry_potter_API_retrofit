package com.example.harry_potter_and_retrofit.presentation.characterlistfragment

import androidx.recyclerview.widget.DiffUtil
import com.example.harry_potter_and_retrofit.domain.model.CharacterItem

class CharacterListDiffCallback(
    private val oldList: List<CharacterItem>,
    private val newList: List<CharacterItem>
): DiffUtil.Callback(

) {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}