package muhammadbahaa.actorbox.data.remote

import io.reactivex.Single
import muhammadbahaa.actorbox.data.model.actor.ActorDetails
import muhammadbahaa.actorbox.data.model.base.BaseResponse
import muhammadbahaa.actorbox.data.model.base.ImageBaseResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by muhammadbahaa on 2019-06-12.
 */
interface ApiService {

    @GET("person/popular")
    fun getActors(): Single<BaseResponse>

    @GET("person/{person_id}/images")
    fun getActorsImages(@Path("person_id") person_id: String): Single<ImageBaseResponse>

    @GET("person/{person_id}")
    fun getActorDetails(@Path("person_id") person_id: String): Single<ActorDetails>

    @GET("search/person")
    fun getSearchResult(@Query("query") query: String): Single<BaseResponse>

}
