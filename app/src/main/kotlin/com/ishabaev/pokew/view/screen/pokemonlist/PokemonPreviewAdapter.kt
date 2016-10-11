package com.ishabaev.pokemonwiki.screen.pokemonlist

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ishabaev.pokew.R
import com.ishabaev.pokew.model.PokemonPreview

class PokemonPreviewAdapter(private var mPreviews: MutableList<PokemonPreview>,
                            private var recyclerView: RecyclerView) :
        RecyclerView.Adapter<PokemonPreviewViewHolder>() {

    private var totalItemCount: Int = 0
    private var lastVisibleItem: Int = 0
    private var mLoading: Boolean = false
    private val visibleThreshold = 6
    private var mOnLoadMoreListener: OnLoadMoreListener? = null
    private var mOnItemClickListener: OnItemClickListener? = null;

    init {
        if (recyclerView.layoutManager is StaggeredGridLayoutManager) {
            val linearLayoutManager = recyclerView.layoutManager as StaggeredGridLayoutManager
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    totalItemCount = linearLayoutManager.itemCount
                    var array: IntArray = linearLayoutManager.findLastVisibleItemPositions(null)
                    lastVisibleItem = array[array.size - 1]
                    if (!mLoading && totalItemCount <= lastVisibleItem + visibleThreshold) {
                        if (mOnLoadMoreListener != null) {
                            mOnLoadMoreListener!!.onLoadMore()
                        }
                        mLoading = true
                    }
                }
            })
        }
    }

    private val mInternalListener = View.OnClickListener { view ->
        if (mOnItemClickListener != null) {
            val preview = view.tag as PokemonPreview
            mOnItemClickListener!!.onClick(view, preview)
        }
    }

    fun getLastVisibleItem(): Int {
        return lastVisibleItem;
    }

    fun setLoading(loading: Boolean) {
        mLoading = loading
    }

    fun setOnLoadMoreListener(onLoadMoreListener: OnLoadMoreListener) {
        mOnLoadMoreListener = onLoadMoreListener
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        mOnItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonPreviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon_preview, parent, false)
        return PokemonPreviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonPreviewViewHolder, position: Int) {
        holder.bind(mPreviews[position])
        holder.spriteImageView.setOnClickListener(mInternalListener)
        holder.spriteImageView.tag = mPreviews[position]
    }

    override fun getItemCount(): Int {
        return mPreviews.size
    }

    fun changeDataSet(pokemonPreviews: List<PokemonPreview>) {
        mPreviews.clear()
        mPreviews.addAll(pokemonPreviews)
        notifyDataSetChanged()
    }

    fun addDataSet(pokemonPreviews: List<PokemonPreview>) {
        var startPosition = mPreviews.size
        mPreviews.addAll(pokemonPreviews)
        notifyItemRangeInserted(startPosition, pokemonPreviews.size)
    }

    interface OnLoadMoreListener {
        fun onLoadMore()
    }

    interface OnItemClickListener {
        fun onClick(v: View, preview: PokemonPreview)
    }
}
