package muhammadbahaa.actorbox.ui.actor_details

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import muhammadbahaa.actorbox.data.model.actor.ActorDetails
import muhammadbahaa.actorbox.data.model.actor.ActorProfile
import muhammadbahaa.actorbox.data.model.base.ImageBaseResponse
import muhammadbahaa.actorbox.data.remote.ApiClient
import muhammadbahaa.actorbox.data.remote.ApiService

/**
 * Created by muhammadbahaa on 2019-06-13.
 */
class ActorDetailsViewModel : ViewModel() {

    private var actorImages: MutableLiveData<List<ActorProfile>>? = null
    private var actorDetails: MutableLiveData<ActorDetails>? = null

    fun getActorsImages(): MutableLiveData<List<ActorProfile>>? {
        if (actorImages == null) {
            actorImages = MutableLiveData<List<ActorProfile>>()
        }
        return actorImages
    }

    fun getActorDetails(): MutableLiveData<ActorDetails>? {
        if (actorDetails == null) {
            actorDetails = MutableLiveData<ActorDetails>()
        }
        return actorDetails
    }


    fun loadActorsImages(actorId: String) {

        var apiService = ApiClient.provideRetrofitInterface().create(ApiService::class.java)

        apiService.getActorsImages(actorId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : SingleObserver<ImageBaseResponse> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onSuccess(value: ImageBaseResponse?) {
                    if (actorImages != null) {
                        getActorsImages()?.setValue(value?.getProfiles())
                    }
                }

                override fun onError(e: Throwable) {
                    Log.e( javaClass.simpleName, e.message)
                }
            })
    }

    fun loadActorDetails(actorId: String) {

        var apiService = ApiClient.provideRetrofitInterface().create(ApiService::class.java)

        apiService.getActorDetails(actorId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : SingleObserver<ActorDetails> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onSuccess(value: ActorDetails?) {
                    if (actorDetails != null) {
                        getActorDetails()?.setValue(value)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.e( javaClass.simpleName, e.message)
                }
            })
    }
}