package com.ishabaev.pokemonwiki.screen.pokemondetails

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import com.ishabaev.pokemonwiki.repository.RepositoryProvider
import com.ishabaev.pokemonwiki.util.ImageHelper
import com.ishabaev.pokew.R
import com.ishabaev.pokew.model.PokemonPreview

class PokemonDetailsActivity : AppCompatActivity(), PokemonDetailsView {

    companion object {
        val ID_KEY: String = "id"
        val NAME_KEY: String = "name"
        fun showActivity(activity: Activity, preview: PokemonPreview) {
            var intent = Intent(activity, PokemonDetailsActivity::class.java)
            intent.putExtra(PokemonDetailsActivity.ID_KEY, preview.getId())
            intent.putExtra(PokemonDetailsActivity.NAME_KEY, preview.getName())
            activity.startActivity(intent)
        }
    }

    private lateinit var mPresenter: PokemonDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        var id = intent.getIntExtra(ID_KEY, -1)
        if (id >= 0) {
            initPoster(id)
        }
        initToolbar()
        createPresenter()
        mPresenter.load(id)
    }

    private fun initPoster(id: Int) {
        var imageView = findViewById(R.id.poster) as ImageView
        ImageHelper.loadSprite(imageView, id)
    }

    private fun initToolbar() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowHomeEnabled(true)
        }
        val name = intent.getStringExtra(NAME_KEY)
        val appBarLayout = findViewById(R.id.toolbar_layout) as CollapsingToolbarLayout
        appBarLayout.title = name
    }

    private fun createPresenter() {
        mPresenter = PokemonDetailsPresenter(this, RepositoryProvider.sPokemonRepository)
    }

    override fun showDescription(description: String) {

    }
}