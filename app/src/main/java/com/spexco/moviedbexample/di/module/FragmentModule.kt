package com.spexco.moviedbexample.di.module

import com.spexco.moviedbexample.view.GenreFragment
import com.spexco.moviedbexample.view.PagerItemGenreFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeCategoryFragment(): GenreFragment


    @ContributesAndroidInjector
    abstract fun contributePagerItemGenreFragment(): PagerItemGenreFragment

}