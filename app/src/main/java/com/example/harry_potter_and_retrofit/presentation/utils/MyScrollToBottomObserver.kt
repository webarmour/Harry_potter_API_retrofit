package com.example.harry_potter_and_retrofit.presentation.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.harry_potter_and_retrofit.presentation.ui.forumfragment.ForumAdapter


class MyScrollToBottomObserver(
    private val recyclerView: RecyclerView,
    private val layoutManager: LinearLayoutManager,
    private val adapter: ForumAdapter
): RecyclerView.AdapterDataObserver() {

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        super.onItemRangeInserted(positionStart, itemCount)

        val count = adapter.itemCount
        val lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition()
        val loading = lastVisiblePosition == -1
        val atBottom = positionStart >= count-1 && lastVisiblePosition == positionStart-1

        if (loading || atBottom) {
            recyclerView.scrollToPosition(positionStart)
        }
    }
}