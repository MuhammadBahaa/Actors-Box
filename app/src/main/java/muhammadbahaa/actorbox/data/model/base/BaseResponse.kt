package muhammadbahaa.actorbox.data.model.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import muhammadbahaa.actorbox.data.model.actor.Actor


/**
 * Created by muhammadbahaa on 2019-06-13.
 */
class BaseResponse {

    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null
    @SerializedName("actors")
    @Expose
    var actors: List<Actor>? = null

}