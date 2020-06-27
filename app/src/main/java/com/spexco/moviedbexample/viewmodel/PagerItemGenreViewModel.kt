package com.spexco.moviedbexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.spexco.moviedbexample.adapter.FilmAdapter
import com.spexco.moviedbexample.model.FilmResult
import com.spexco.moviedbexample.model.Genre
import com.spexco.moviedbexample.remote.MovieService
import com.yenen.ahmet.basecorelibrary.base.model.LiveDataResponseModel
import com.yenen.ahmet.basecorelibrary.base.utility.ProjectConstants
import com.yenen.ahmet.basecorelibrary.base.viewmodel.BaseRxViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PagerItemGenreViewModel  @Inject constructor(
    private val movieService: MovieService
):BaseRxViewModel() {

    val adapter = FilmAdapter()

    private var resultGenresLiveDataResponseModel:
        MutableLiveData<LiveDataResponseModel<List<FilmResult>>> ? =null


    fun getGenresLiveData(id:Long): LiveData<LiveDataResponseModel<List<FilmResult>>> {
        if(resultGenresLiveDataResponseModel == null){
            getFilms(id)
            resultGenresLiveDataResponseModel =MutableLiveData()
        }
        return resultGenresLiveDataResponseModel!!
    }


     private fun getFilms(id:Long) {
        addDisposable(
            movieService.getFilms(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if (!it.results.isNullOrEmpty()) {
                        resultGenresLiveDataResponseModel?.value =
                            LiveDataResponseModel(
                                it.results,
                                ProjectConstants.SUCCESS_STATUS,
                                ""
                            )
                    } else {
                        resultGenresLiveDataResponseModel?.value =
                            LiveDataResponseModel(
                                null,
                                ProjectConstants.SUCCESS_STATUS_EMPTY_DATA,
                                "Film bulunamadı!"
                            )
                    }
                }, {
                    resultGenresLiveDataResponseModel?.value =
                        LiveDataResponseModel(
                            null,
                            ProjectConstants.ERROR_STATUS,
                            it.toString()
                        )
                })
        )
    }
}