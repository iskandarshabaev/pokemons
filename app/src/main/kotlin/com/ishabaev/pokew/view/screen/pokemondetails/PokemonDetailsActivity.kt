package com.ishabaev.pokemonwiki.screen.pokemondetails

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ishabaev.pokemonwiki.repository.RepositoryProvider
import com.ishabaev.pokemonwiki.util.ImageHelper
import com.ishabaev.pokew.R
import com.ishabaev.pokew.model.PokemonPreview
import com.ishabaev.pokew.model.PokemonStat

class PokemonDetailsActivity : AppCompatActivity(), PokemonDetailsView {

    companion object {
        val ID_KEY: String = "id"
        val NAME_KEY: String = "name"
        fun showActivity(activity: Activity, preview: PokemonPreview) {
            var intent = Intent(activity, PokemonDetailsActivity::class.java)
            intent.putExtra(PokemonDetailsActivity.ID_KEY, preview.id)
            intent.putExtra(PokemonDetailsActivity.NAME_KEY, preview.name)
            activity.startActivity(intent)
        }
    }

    private lateinit var mPresenter: PokemonDetailsPresenter
    private lateinit var mBaseExperienceTextView: TextView
    private lateinit var mHeightTextView: TextView
    private lateinit var mWeightTextView: TextView
    private lateinit var mStatsLL: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        findViews()
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

    private fun findViews() {
        mBaseExperienceTextView = findViewById(R.id.base_experience) as TextView
        mHeightTextView = findViewById(R.id.height) as TextView
        mWeightTextView = findViewById(R.id.weight) as TextView
        mStatsLL = findViewById(R.id.stats) as LinearLayout
    }

    override fun showWeight(weight: String) {
        mWeightTextView.text = weight
    }

    override fun showHeight(height: String) {
        mHeightTextView.text = height
    }

    override fun baseExp(exp: String) {
        mBaseExperienceTextView.text = exp
    }

    override fun showStat(stats: List<PokemonStat>) {
        mStatsLL.removeAllViews()
        stats.forEach({ stat ->
            var textView = layoutInflater.inflate(R.layout.text_view, null) as TextView
            textView.text = stat.statVal.name
            mStatsLL.addView(textView)
        })
    }

}