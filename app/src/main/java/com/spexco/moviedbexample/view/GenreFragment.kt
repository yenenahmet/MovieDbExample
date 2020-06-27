package com.spexco.moviedbexample.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.spexco.moviedbexample.R
import com.spexco.moviedbexample.adapter.GenrePagerAdapter
import com.spexco.moviedbexample.custom.ZoomOutPageTransformer
import com.spexco.moviedbexample.databinding.FragmentGenreBinding
import com.spexco.moviedbexample.databinding.ItemFilmBinding
import com.spexco.moviedbexample.model.FilmResult
import com.spexco.moviedbexample.viewmodel.GenreViewModel
import com.yenen.ahmet.basecorelibrary.base.adapter.BaseViewBindingRecyclerViewAdapter
import com.yenen.ahmet.basecorelibrary.base.ui.BaseDaggerFragment
import com.yenen.ahmet.basecorelibrary.base.utility.ProjectConstants


class GenreFragment : BaseDaggerFragment<GenreViewModel, FragmentGenreBinding>(
    GenreViewModel::class.java,
    R.layout.fragment_genre
), BaseViewBindingRecyclerViewAdapter.ClickListener<FilmResult, ItemFilmBinding> {


    override fun initViewModel(viewModel: GenreViewModel) {
        binding.viewModel = viewModel
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getGenresLiveData().observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it.status) {
                    ProjectConstants.ERROR_STATUS -> {
                        binding.tvError.text = it.description
                    }
                    ProjectConstants.SUCCESS_STATUS_EMPTY_DATA -> {
                        binding.tvError.text = it.description
                    }
                    ProjectConstants.SUCCESS_STATUS -> {
                        val genreAdapter = GenrePagerAdapter(childFragmentManager, it.data!!, this)
                        binding.viewPager.adapter = genreAdapter
                        binding.viewPager.setPageTransformer(true, ZoomOutPageTransformer())
                        binding.tvError.visibility = View.GONE
                    }
                }

                binding.progressWheel.stopSpinning()
                binding.progressWheel.visibility = View.GONE
            }
        })
    }

    override fun onItemClick(item: FilmResult, position: Int, rowBinding: ItemFilmBinding) {
        val bundle = Bundle().apply {
            putParcelable("Film", item)
        }

        findNavController().navigate(R.id.action_genreFragment_to_filmDetailFragment, bundle)
    }


}
