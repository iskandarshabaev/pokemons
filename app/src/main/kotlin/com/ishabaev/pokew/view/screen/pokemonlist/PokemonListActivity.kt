package com.ishabaev.pokemonwiki.screen.pokemonlist


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.ishabaev.pokemonwiki.repository.RepositoryProvider
import com.ishabaev.pokemonwiki.screen.pokemondetails.PokemonDetailsActivity
import com.ishabaev.pokew.R
import com.ishabaev.pokew.model.PokemonPreview
import java.util.*

class PokemonListActivity : AppCompatActivity(), PokemonListView {

    private val POSITION_KEY: String = "position"
    private var mPresenter: PokemonListPresenter
    private lateinit var pokemonRecyclerView: RecyclerView

    init {
        mPresenter = PokemonListPresenter(this, RepositoryProvider.sPokemonRepository)
    }

    private lateinit var mAdapter: PokemonPreviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)
        pokemonRecyclerView = findViewById(R.id.pokemon_list) as RecyclerView
        initRecyclerView(pokemonRecyclerView)

        /*if (savedInstanceState != null) {
            var position = savedInstanceState!!.get(POSITION_KEY) as Int
            mPresenter.restore(position)
        } else {
            mPresenter.nextPokemonList()
        }*/
    }

    private fun initRecyclerView(pokemonRecyclerView: RecyclerView) {
        val animator = DefaultItemAnimator()
        pokemonRecyclerView.itemAnimator = animator
        val spanCount = resources.getInteger(R.integer.pokemon_columns)
        val manager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
        val spacing = resources.getDimensionPixelSize(R.dimen.base_8)
        val includeEdge = true
        pokemonRecyclerView.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
        pokemonRecyclerView.layoutManager = manager


        mAdapter = PokemonPreviewAdapter(ArrayList<PokemonPreview>(), pokemonRecyclerView)
        pokemonRecyclerView.adapter = mAdapter
        pokemonRecyclerView.addOnScrollListener(OnScrollListener())
        mAdapter.setOnLoadMoreListener(object : PokemonPreviewAdapter.OnLoadMoreListener {
            override fun onLoadMore() {
                mPresenter.nextPokemonList()
            }
        })
        mAdapter.setOnItemClickListener(object : PokemonPreviewAdapter.OnItemClickListener {
            override fun onClick(v: View, preview: PokemonPreview) {
                PokemonDetailsActivity.showActivity(this@PokemonListActivity, preview)
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putInt(POSITION_KEY, mAdapter.getLastVisibleItem())
    }

    class OnScrollListener() : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
        }
    }

    override fun showException() {

    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun addPokemons(pokemonPreviews: List<PokemonPreview>) {
        mAdapter.addDataSet(pokemonPreviews)
        mAdapter.setLoading(false)
    }

    override fun setPokemons(pokemonPreviews: List<PokemonPreview>) {
        mAdapter.changeDataSet(pokemonPreviews)
        pokemonRecyclerView.scrollToPosition(pokemonPreviews.size)
        pokemonRecyclerView.layoutManager.scrollToPosition(pokemonPreviews.size)
        mAdapter.setLoading(false)
    }

    override fun onResume() {
        super.onResume()
    }
}
