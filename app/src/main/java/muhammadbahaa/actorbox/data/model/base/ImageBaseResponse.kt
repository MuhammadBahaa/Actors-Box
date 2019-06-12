package muhammadbahaa.actorbox.data.model.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import muhammadbahaa.actorbox.data.model.actor.ActorProfile


/**
 * Created by muhammadbahaa on 2019-06-13.
 */
class ImageBaseResponse {
    @SerializedName("id")
    @Expose
    private var id: Int? = null
    @SerializedName("profiles")
    @Expose
    private var profiles: List<ActorProfile>? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getProfiles(): List<ActorProfile>? {
        return profiles
    }

    fun setProfiles(profiles: List<ActorProfile>) {
        this.profiles = profiles
    }
}