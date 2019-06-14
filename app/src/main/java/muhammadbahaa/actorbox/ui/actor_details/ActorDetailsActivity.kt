package muhammadbahaa.actorbox.ui.actor_details

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.viewpagerindicator.CirclePageIndicator
import muhammadbahaa.actorbox.R
import muhammadbahaa.actorbox.data.model.actor.ActorProfile
import muhammadbahaa.actorbox.ui.slider.SlidingImageAdapter
import java.util.*

/**
 * Created by muhammadbahaa on 2019-06-14.
 */

class ActorDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actor_details)

        val intent = intent
        val id = intent.getStringExtra("actor_id")

        val viewModel = ViewModelProviders.of(this).get(ActorDetailsViewModel::class.java)

        viewModel.loadActorsImages(id)

        viewModel.getActorsImages()?.observe(this, object : Observer<List<ActorProfile>> {
            override fun onChanged(actorProfiles : List<ActorProfile>?) {
                if (actorProfiles != null) {
                    initViewPager(actorProfiles)
                }
            }
        })
    }


    private fun initViewPager(imageModelArrayList: List<ActorProfile>) {

        mPager = findViewById(R.id.pager) as ViewPager
        mPager!!.adapter = SlidingImageAdapter(this@ActorDetailsActivity, imageModelArrayList!!)

        val indicator = findViewById(R.id.indicator) as CirclePageIndicator

        indicator.setViewPager(mPager)

        val density = resources.displayMetrics.density

        //Set circle indicator radius
        indicator.setRadius(5 * density)

        NUM_PAGES = imageModelArrayList!!.size

        // Auto start of viewpager
        val handler = Handler()
        val Update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            mPager!!.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 3000, 3000)

        // Pager listener over indicator
        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                currentPage = position

            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {

            }

            override fun onPageScrollStateChanged(pos: Int) {

            }
        })



    }

    companion object {

        private var mPager: ViewPager? = null
        private var currentPage = 0
        private var NUM_PAGES = 0
    }

}

