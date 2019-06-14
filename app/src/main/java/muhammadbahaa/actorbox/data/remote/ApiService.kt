package muhammadbahaa.actorbox.data.remote

import io.reactivex.Single
import muhammadbahaa.actorbox.data.model.base.BaseResponse
import muhammadbahaa.actorbox.data.model.base.ImageBaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by muhammadbahaa on 2019-06-12.
 */
interface ApiService {

    @GET("person/popular")
    fun getActors(): Single<BaseResponse>

    @GET("person/{person_id}/images")
    fun getActorsImages(@Path("person_id") person_id: String): Single<ImageBaseResponse>
}
