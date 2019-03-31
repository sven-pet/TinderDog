package scomas.se.tinderdog

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.StackFrom
import kotlinx.android.synthetic.main.activity_main.*
import scomas.se.tinderdog.adapters.DogAdapter
import scomas.se.tinderdog.viewmodels.DogViewModel
import java.util.logging.Logger

class MainActivity : AppCompatActivity(), CardStackListener {


    lateinit var mViewModel : DogViewModel
    lateinit var mAdapter: RecyclerView.Adapter<*>
    val LOG = Logger.getLogger(this.javaClass.name)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = ViewModelProviders.of(this).get(DogViewModel::class.java)

        val manager = CardStackLayoutManager(this,this)

        card_stack_view.layoutManager = manager
        manager.setStackFrom(StackFrom.Top)
        manager.setVisibleCount(4)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        setupListeners()
        mViewModel.fetchDog()
    }

    fun setupListeners() {

        val dogListener = Observer<Int> {
            result ->
            System.out.println()
            mAdapter = DogAdapter(mViewModel.dogs, this)
            card_stack_view.adapter=mAdapter
        }
        mViewModel.finisheddog.observe(this, dogListener)


    }

    override fun onCardDisappeared(view: View?, position: Int) {
        ;
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
        ;
    }

    override fun onCardSwiped(direction: Direction?) {
        LOG.warning("Card swiped to : " + direction )
        if(direction!!.equals("Right")){

        }
        mViewModel.fetchDog()
    }

    override fun onCardCanceled() {
        ;
    }

    override fun onCardAppeared(view: View?, position: Int) {
        ;
    }

    override fun onCardRewound() {
        ;
    }
}
