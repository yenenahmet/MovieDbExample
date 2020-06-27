package com.spexco.moviedbexample.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.spexco.moviedbexample.R
import com.spexco.moviedbexample.databinding.ItemFilmBinding
import com.spexco.moviedbexample.databinding.PagerItemGenreBinding
import com.spexco.moviedbexample.model.FilmResult
import com.spexco.moviedbexample.viewmodel.PagerItemGenreViewModel
import com.yenen.ahmet.basecorelibrary.base.adapter.BaseViewBindingRecyclerViewAdapter
import com.yenen.ahmet.basecorelibrary.base.ui.BaseDaggerFragment
import com.yenen.ahmet.basecorelibrary.base.utility.ProjectConstants

class PagerItemGenreFragment
constructor(private val listener: BaseViewBindingRecyclerViewAdapter.ClickListener<FilmResult, ItemFilmBinding>) :
    BaseDaggerFragment<PagerItemGenreViewModel, PagerItemGenreBinding>(
        PagerItemGenreViewModel::class.java,
        R.layout.pager_item_genre
    ) {

    companion object {
        fun newInstance(id: Long, title: String,listener: BaseViewBindingRecyclerViewAdapter.ClickListener<FilmResult, ItemFilmBinding>): PagerItemGenreFragment {
            val fragmentFirst = PagerItemGenreFragment(listener)
            val args = Bundle().apply {
                putLong("id", id)
                putString("title", title)
            }
            fragmentFirst.arguments = args
            return fragmentFirst
        }
    }

    override fun initViewModel(viewModel: PagerItemGenreViewModel) {
        binding.viewModel = viewModel
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val id = arguments?.getLong("id", 0) ?: 0
        viewModel.getGenresLiveData(id).observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it.status) {
                    ProjectConstants.ERROR_STATUS -> {
                        binding.tvError.text = it.description
                    }
                    ProjectConstants.SUCCESS_STATUS_EMPTY_DATA -> {
                        binding.tvError.text = it.description
                    }
                    ProjectConstants.SUCCESS_STATUS -> {
                        viewModel.adapter.setItems(it.data!!)
                        binding.tvError.visibility = View.GONE
                    }
                }

                binding.progressWheel.stopSpinning()
                binding.progressWheel.visibility = View.GONE
            }
        })
    }

    override fun onBindingCreate(binding: PagerItemGenreBinding) {
        binding.tvTitle.text = arguments?.getString("title", "") ?: ""
        viewModel.adapter.setListener(listener)
    }


}
