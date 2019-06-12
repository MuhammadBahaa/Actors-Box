package muhammadbahaa.actorbox.data.model.actor

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by muhammadbahaa on 2019-06-13.
 */

class ActorProfile {

    @SerializedName("aspect_ratio")
    @Expose
    var aspectRatio: Double? = null
    @SerializedName("file_path")
    @Expose
    var filePath: String? = null
    @SerializedName("height")
    @Expose
    var height: Int? = null
    @SerializedName("iso_639_1")
    @Expose
    var iso6391: Any? = null
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Int? = null
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null
    @SerializedName("width")
    @Expose
    var width: Int? = null

}