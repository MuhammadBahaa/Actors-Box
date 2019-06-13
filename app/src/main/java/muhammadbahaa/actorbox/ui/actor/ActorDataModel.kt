package muhammadbahaa.actorbox.ui.actor

/**
 * Created by muhammadbahaa on 2019-06-13.
 */
class ActorDataModel {

    lateinit var actorName: String
    lateinit var actorProfileUrl:String

    fun ActorDataModel(actorName: String, actorProfileUrl: String){

        this.actorName = actorName
        this.actorProfileUrl = actorProfileUrl
    }
}