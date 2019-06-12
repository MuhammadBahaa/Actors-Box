package muhammadbahaa.actorbox.data.model.actor

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by muhammadbahaa on 2019-06-13.
 */

class Actor {

    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("profile_path")
    @Expose
    var profilePath: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("known_for")
    @Expose
    var knownFor: List<KnownFor>? = null
    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null

}