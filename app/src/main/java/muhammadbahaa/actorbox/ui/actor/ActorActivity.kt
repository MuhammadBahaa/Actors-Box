package muhammadbahaa.actorbox.ui.actor

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import muhammadbahaa.actorbox.R
import muhammadbahaa.actorbox.data.model.actor.Actor


class ActorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(ActorViewModel::class.java!!)
        viewModel.getActors().observe(this, Observer<List<Actor>>{ actors ->
            // update UI
        })
    }

}
