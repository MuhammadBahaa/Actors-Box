package muhammadbahaa.actorbox.ui.actor


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.EditText
import muhammadbahaa.actorbox.R
import muhammadbahaa.actorbox.data.model.actor.Actor
import muhammadbahaa.actorbox.databinding.ActivityActorBinding


/**
 * Created by muhammadbahaa on 2019-06-12.
 */

class ActorActivity : AppCompatActivity() {

    private var binding: ActivityActorBinding? = null
    private var viewModel:ActorViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_actor)

        viewModel = ViewModelProviders.of(this).get(ActorViewModel::class.java)

        viewModel!!.getActors().observe(this, Observer<List<Actor>>{ actors ->
            if (actors != null) {
                updateUI(actors)
            }
        })

        binding?.searchButton?.setOnClickListener{
            showSearchDialog()
        }
    }

    fun updateUI(actors : List<Actor>){

        val layoutManager = GridLayoutManager(this@ActorActivity ,2)
        val actorsAdapter = ActorAdapter(actors, this@ActorActivity)

        binding?.recyclerView?.setLayoutManager(layoutManager)
        binding?.recyclerView?.setAdapter(actorsAdapter)
    }

    fun showSearchDialog() {

        val context = this
        val builder = AlertDialog.Builder(context)
        val view = layoutInflater.inflate(R.layout.dialog_search, null)
        val searchEditText = view.findViewById(R.id.searchEditText) as EditText

        builder.setTitle("Search")
        builder.setView(view)

        builder.setPositiveButton(android.R.string.ok) { dialog, p1 ->
            val searchQuery = searchEditText.text
            var isValid = true

            if (searchQuery.isBlank()) {
                searchEditText.error = getString(R.string.validation_empty)
                isValid = false
            }

            if (isValid) {
                viewModel?.getSearchReslts(searchQuery.toString())
            }

            if (isValid) {
                dialog.dismiss()
            }
        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, p1 ->
            dialog.cancel()
        }

        builder.show()
    }

}
