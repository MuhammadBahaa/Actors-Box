package muhammadbahaa.actorbox.data.remote

import io.reactivex.Single
import muhammadbahaa.actorbox.data.model.base.BaseResponse
import retrofit2.http.GET

/**
 * Created by muhammadbahaa on 2019-06-12.
 */
interface ApiService {

    @GET("person/popular")
    fun getArticles(): Single<BaseResponse>
}

object ApiParams {

}
