package com.spexco.moviedbexample.view

import com.spexco.moviedbexample.R
import com.spexco.moviedbexample.databinding.ActivityMainBinding
import com.spexco.moviedbexample.viewmodel.MainViewModel
import com.yenen.ahmet.basecorelibrary.base.ui.BaseDaggerActivity

class MainActivity : BaseDaggerActivity<MainViewModel, ActivityMainBinding>(
    MainViewModel::class.java,
    R.layout.activity_main
) {

    override fun initViewModel(viewModel: MainViewModel) {
        binding.viewModel = viewModel
    }


}
