package com.example.harry_potter_and_retrofit.presentation.ui.characterlistfragment


import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.harry_potter_and_retrofit.domain.model.CharacterItem

class CharacterListAdapter(
) :
    ListAdapter<CharacterItem, CharacterListAdapter.ViewHolder>(Comparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val composeView = ComposeView(parent.context)
        return ViewHolder(composeView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val characterItem = getItem(position)
        holder.bind(characterItem)

    }


    class ViewHolder(private val composeView: ComposeView) : RecyclerView.ViewHolder(composeView) {
        fun bind(characterItem: CharacterItem) {

            composeView.apply{
                setContent {
                    CharacterItemCompose(characterItem = characterItem)
                }
            }
        }
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