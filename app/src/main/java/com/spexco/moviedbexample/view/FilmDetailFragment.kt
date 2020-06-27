package com.spexco.moviedbexample.view

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.spexco.moviedbexample.R
import com.spexco.moviedbexample.databinding.FragmentFilmDetailBinding
import com.spexco.moviedbexample.model.FilmResult
import com.spexco.moviedbexample.viewmodel.FilmDetailViewModel
import com.yenen.ahmet.basecorelibrary.base.ui.BaseFragment
import jp.wasabeef.glide.transformations.BlurTransformation


class FilmDetailFragment : BaseFragment<FilmDetailViewModel, FragmentFilmDetailBinding>(
    FilmDetailViewModel::class.java,
    R.layout.fragment_film_detail
) {

    override fun initViewModel(viewModel: FilmDetailViewModel) {
        binding.viewModel = viewModel
    }


    override fun onBindingCreate(binding: FragmentFilmDetailBinding) {
        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onBundle(bundle: Bundle) {
        bundle.getParcelable<FilmResult>("Film")?.let {
            binding.imgPoster.transitionName = it.id.toString()
            binding.tvDate.text = it.release_date
            binding.tvVoteCount.text = it.vote_count.toString()
            binding.tvImdb.text = it.vote_average.toString()
            binding.tvOrginalTitle.text = it.original_title
            binding.tvOriginalLanguage.text = it.original_language
            binding.tvOverview.text = it.overview
            binding.tvTitle.text = it.title

            Glide.with(binding.root.context)
                .load("http://image.tmdb.org/t/p/w185${it.poster_path}")
                .into(binding.imgPoster)

            Glide.with(binding.root.context)
                .load("http://image.tmdb.org/t/p/w185${it.backdrop_path}")
                .transform(BlurTransformation())
                .into(binding.imgBackgroundPoster)
        }

    }


}
