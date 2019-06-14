package muhammadbahaa.actorbox.ui.actor

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import muhammadbahaa.actorbox.data.model.actor.Actor
import muhammadbahaa.actorbox.databinding.ItemActorBinding
import muhammadbahaa.actorbox.ui.actor_details.ActorDetailsActivity
import muhammadbahaa.actorbox.utils.Constants



/**
 * Created by muhammadbahaa on 2019-06-14.
 */

class ActorAdapter(private val actorList: List<Actor>?, private val context: Context) :
    RecyclerView.Adapter<ActorAdapter.ActorItemView>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorItemView {
        val layoutInflater = LayoutInflater.from(parent.context)


        val binding: ItemActorBinding =
            DataBindingUtil.inflate(layoutInflater, muhammadbahaa.actorbox.R.layout.item_actor, parent, false)

        return ActorItemView(binding)
    }

    override fun onBindViewHolder(holder: ActorItemView, position: Int) {
        val actor = actorList!![position]
        holder.binding.setActor(actor)
        holder.binding.executePendingBindings()
        Glide.with(context)
            .load(Constants.Image_BASE_URL + actor.profile_path)
            .into(holder.binding.imageViewActorProfile)
    }

    override fun getItemCount(): Int {
        return if (actorList != null && actorList.size > 0) actorList.size else 0
    }


    inner class ActorItemView(var binding: ItemActorBinding) : RecyclerView.ViewHolder(binding.getRoot()),
        View.OnClickListener {

        init {
            this.binding.getRoot().setOnClickListener(this)

        }

        override fun onClick(p0: View?) {
            val intent = Intent(context, ActorDetailsActivity::class.java)
            if (actorList != null) {
                intent.putExtra("actor_id", actorList.get(adapterPosition).id.toString())
            }
            context.startActivity(intent)
        }
    }
}