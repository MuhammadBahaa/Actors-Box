package muhammadbahaa.actorbox.ui.actor


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import muhammadbahaa.actorbox.R
import muhammadbahaa.actorbox.data.model.actor.Actor
import muhammadbahaa.actorbox.databinding.ActivityActorBinding


/**
 * Created by muhammadbahaa on 2019-06-12.
 */

class ActorActivity : AppCompatActivity() {

    private var binding: ActivityActorBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_actor)

        val viewModel = ViewModelProviders.of(this).get(ActorViewModel::class.java)

        viewModel.getActors().observe(this, Observer<List<Actor>>{ actors ->
            if (actors != null) {
                updateUI(actors)
            }
        })
    }

    fun updateUI(actors : List<Actor>){

        val layoutManager = GridLayoutManager(this@ActorActivity ,2)
        val actorsAdapter = ActorAdapter(actors, this@ActorActivity)

        binding?.recyclerView?.setLayoutManager(layoutManager)
        binding?.recyclerView?.setAdapter(actorsAdapter)


    }

}
