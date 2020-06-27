package com.spexco.moviedbexample.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.spexco.moviedbexample.databinding.ItemFilmBinding
import com.spexco.moviedbexample.model.FilmResult
import com.spexco.moviedbexample.model.Genre
import com.spexco.moviedbexample.view.PagerItemGenreFragment
import com.yenen.ahmet.basecorelibrary.base.adapter.BaseViewBindingRecyclerViewAdapter


class GenrePagerAdapter constructor(
    fragmentManager: FragmentManager,
    private val items: List<Genre>,
    private val listener: BaseViewBindingRecyclerViewAdapter.ClickListener<FilmResult, ItemFilmBinding>
) :
    FragmentPagerAdapter(
        fragmentManager,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {

    override fun getItem(position: Int): Fragment {
        return PagerItemGenreFragment.newInstance(items[position].id,items[position].name,listener)
    }

    override fun getCount(): Int {
        return items.size
    }

}