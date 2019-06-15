package muhammadbahaa.actorbox.ui.actor

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import muhammadbahaa.actorbox.data.model.actor.Actor
import muhammadbahaa.actorbox.data.model.base.BaseResponse
import muhammadbahaa.actorbox.data.remote.ApiClient
import muhammadbahaa.actorbox.data.remote.ApiService

/**
 * Created by muhammadbahaa on 2019-06-13.
 */
class ActorViewModel : ViewModel() {

    private val actors: MutableLiveData<List<Actor>> by lazy {
        MutableLiveData<List<Actor>>().also {
            loadActors()
        }
    }

    fun getActors(): LiveData<List<Actor>> {
        return actors
    }

    fun loadActors() {

        var apiService = ApiClient.provideRetrofitInterface().create(ApiService::class.java)

        apiService.getActors().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : SingleObserver<BaseResponse> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onSuccess(value: BaseResponse?) {
                    actors.setValue(value?.results)
                }

                override fun onError(e: Throwable) {
                    Log.e( javaClass.simpleName, e.message)
                }
            })
    }

    fun getSearchReslts(query : String) {

        var apiService = ApiClient.provideRetrofitInterface().create(ApiService::class.java)

        apiService.getSearchResult(query).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : SingleObserver<BaseResponse> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onSuccess(value: BaseResponse?) {
                    actors.setValue(value?.results)
                }

                override fun onError(e: Throwable) {
                    Log.e( javaClass.simpleName, e.message)
                }
            })
    }
}