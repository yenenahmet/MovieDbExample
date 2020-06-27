package com.spexco.moviedbexample.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spexco.moviedbexample.di.scope.ViewModelKey
import com.spexco.moviedbexample.viewmodel.GenreViewModel
import com.spexco.moviedbexample.viewmodel.MainViewModel
import com.spexco.moviedbexample.viewmodel.PagerItemGenreViewModel
import com.yenen.ahmet.basecorelibrary.base.di.factory.AppViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GenreViewModel::class)
    internal abstract fun bindCategoryViewModel(viewModel: GenreViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(PagerItemGenreViewModel::class)
    internal abstract fun bindPagerItemGenreViewModel(viewModel: PagerItemGenreViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(appViewModelFactory: AppViewModelFactory): ViewModelProvider.Factory
}