package com.ishabaev.pokew.view.screen.pokemondetails

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ishabaev.pokemonwiki.repository.RepositoryProvider
import com.ishabaev.pokemonwiki.screen.pokemondetails.PokemonDetailsActivity
import com.ishabaev.pokemonwiki.screen.pokemondetails.PokemonDetailsPresenter
import com.ishabaev.pokemonwiki.screen.pokemondetails.PokemonDetailsView
import com.ishabaev.pokemonwiki.util.ImageHelper
import com.ishabaev.pokew.R
import com.ishabaev.pokew.model.PokemonPreview
import com.ishabaev.pokew.model.PokemonStat

class PokemonDetailsFragment : Fragment(), PokemonDetailsView {

    companion object {
        val ID_KEY: String = "id"
        val NAME_KEY: String = "name"

        fun newInstance(preview: PokemonPreview): PokemonDetailsFragment {
            var args = Bundle()
            args.putInt(PokemonDetailsActivity.ID_KEY, preview.id)
            args.putString(PokemonDetailsActivity.NAME_KEY, preview.name)
            var fragment = PokemonDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var mImageView: ImageView
    private lateinit var mToolbar: Toolbar
    private lateinit var mAppBarLayout: CollapsingToolbarLayout

    private lateinit var mPresenter: PokemonDetailsPresenter
    private lateinit var mBaseExperienceTextView: TextView
    private lateinit var mHeightTextView: TextView
    private lateinit var mWeightTextView: TextView
    private lateinit var mStatsLL: LinearLayout

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.activity_details, container, true)
        findViews(view)
        var id = arguments.getInt(PokemonDetailsActivity.ID_KEY, -1)
        if (id >= 0) {
            initPoster(id)
        }
        initToolbar()
        createPresenter()
        mPresenter.load(id)
        return view
    }

    private fun initPoster(id: Int) {

        ImageHelper.loadSprite(mImageView, id)
    }

    private fun initToolbar() {

        /*activity.setSupportActionBar(mToolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowHomeEnabled(true)
        }
        val name = intent.getStringExtra(PokemonDetailsActivity.NAME_KEY)

        mAppBarLayout.title = name*/
    }

    private fun createPresenter() {
        mPresenter = PokemonDetailsPresenter(this, RepositoryProvider.sPokemonRepository)
    }

    private fun findViews(view: View) {
        mImageView = view.findViewById(R.id.poster) as ImageView
        mToolbar = view.findViewById(R.id.toolbar) as Toolbar
        mAppBarLayout = view.findViewById(R.id.toolbar_layout) as CollapsingToolbarLayout
        mBaseExperienceTextView = view.findViewById(R.id.base_experience) as TextView
        mHeightTextView = view.findViewById(R.id.height) as TextView
        mWeightTextView = view.findViewById(R.id.weight) as TextView
        mStatsLL = view.findViewById(R.id.stats) as LinearLayout
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
            var textView = activity.layoutInflater.inflate(R.layout.text_view, null) as TextView
            textView.text = stat.statVal.name
            mStatsLL.addView(textView)
        })
    }

}
