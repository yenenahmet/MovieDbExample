package com.spexco.moviedbexample.adapter


import com.bumptech.glide.Glide
import com.spexco.moviedbexample.R
import com.spexco.moviedbexample.databinding.ItemFilmBinding
import com.spexco.moviedbexample.model.FilmResult
import com.yenen.ahmet.basecorelibrary.base.adapter.BaseViewBindingRecyclerViewAdapter

class FilmAdapter :
    BaseViewBindingRecyclerViewAdapter<FilmResult, ItemFilmBinding>(R.layout.item_film) {

    override fun setBindingModel(item: FilmResult, binding: ItemFilmBinding, position: Int) {
        binding.tvTitle.text = item.title
        Glide.with(binding.root.context)
            .load("http://image.tmdb.org/t/p/w185${item.poster_path}")
            .into(binding.imageView)
    }


}