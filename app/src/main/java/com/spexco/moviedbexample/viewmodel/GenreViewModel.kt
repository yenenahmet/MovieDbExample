package com.spexco.moviedbexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.spexco.moviedbexample.adapter.GenrePagerAdapter
import com.spexco.moviedbexample.model.Genre
import com.spexco.moviedbexample.remote.MovieService
import com.yenen.ahmet.basecorelibrary.base.model.LiveDataResponseModel
import com.yenen.ahmet.basecorelibrary.base.utility.ProjectConstants
import com.yenen.ahmet.basecorelibrary.base.viewmodel.BaseRxViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GenreViewModel @Inject constructor(
    private val movieService: MovieService
) : BaseRxViewModel() {



    private val resultGenresLiveDataResponseModel by lazy {
        getGenres()
        MutableLiveData<LiveDataResponseModel<List<Genre>>>()
    }

    fun getGenresLiveData(): LiveData<LiveDataResponseModel<List<Genre>>> {
        return resultGenresLiveDataResponseModel
    }


    private fun getGenres() {
        addDisposable(
            movieService.getGenres()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if (!it.genres.isNullOrEmpty()) {
                        resultGenresLiveDataResponseModel.value =
                            LiveDataResponseModel(
                                it.genres,
                                ProjectConstants.SUCCESS_STATUS,
                                ""
                            )
                    } else {
                        resultGenresLiveDataResponseModel.value =
                            LiveDataResponseModel(
                                it.genres,
                                ProjectConstants.SUCCESS_STATUS_EMPTY_DATA,
                                "Tür bulunamadı!"
                            )
                    }
                }, {
                    resultGenresLiveDataResponseModel.value =
                        LiveDataResponseModel(
                            null,
                            ProjectConstants.ERROR_STATUS,
                            it.toString()
                        )
                })
        )
    }

}