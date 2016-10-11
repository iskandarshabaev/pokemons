package com.ishabaev.pokemonwiki.screen.pokemonlist


import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {

        /*if (parent.getChildLayoutPosition(view) == 1) {
            outRect.top = view.resources.getDimensionPixelSize(R.dimen.base_90) - space
        } else {
            outRect.top = space
        }
        outRect.bottom = space*/

        outRect.left = space
        outRect.right = space
        outRect.bottom = space

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) === 0) {
            outRect.top = space
        } else {
            outRect.top = 0
        }
    }
}
