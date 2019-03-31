package scomas.se.tinderdog.adapters

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import scomas.se.tinderdog.R
import scomas.se.tinderdog.models.DogModel

/**
 * Project : TinderDog
 * Package Name : scomas.se.tinderdog.adapters
 * Created by svenpettersson on 2019-03-23.
 */
class DogAdapter(var dogs:MutableList<DogModel.Dog>, var context: Activity) : RecyclerView.Adapter<DogAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.dog_cardview, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return  dogs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val dog = dogs.get(position)
        val titleText = dog.name
        holder.dogName.setText(titleText)

        val breedText = dog.breedName
        holder.dogBreed.setText(breedText)

        val tempremepntText = dog.temperament
        holder.dogTemprement.setText(tempremepntText)

        val weightText = context.getString(R.string.weight, dog.weight)
        holder.dogWeight.setText(weightText)

        val heightText = context.getString(R.string.height, dog.height)
        holder.dogHeight.setText(heightText)

        val ageText = context.getString(R.string.age, dog.age)
        holder.dogAge.setText(ageText)

        val imageUrl = dog.image
        Glide.with(context).load(imageUrl).into(holder.dogImage)

       /*holder.channelRoot.setOnClickListener {
            click ->
            val intent = Intent(context, SwedishRadioDetailedActivity::class.java)
            intent.putExtra("radioURL", channel.programimage)
            intent.putExtra("radioName", channel.name)
            intent.putExtra("radioDescription", channel.description)

            // start your next activity
            context.startActivity(intent)
            context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }*/
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var dogImage : ImageView
        var dogName: TextView
        var dogBreed: TextView
        var dogTemprement: TextView
        var dogWeight: TextView
        var dogHeight: TextView
        var dogAge: TextView
        //var channelRoot: ConstraintLayout

        init {
            dogImage = view.findViewById<ImageView>(R.id.card_view_image) as ImageView
            dogName = view.findViewById<View>(R.id.card_view_label) as TextView
            dogBreed = view.findViewById<View>(R.id.card_view_breed) as TextView
            dogTemprement = view.findViewById<View>(R.id.card_view_temprement) as TextView
            dogWeight = view.findViewById<View>(R.id.card_view_weight) as TextView
            dogHeight = view.findViewById<View>(R.id.card_view_height) as TextView
            dogAge = view.findViewById<View>(R.id.card_view_age) as TextView

            //channelRoot = view.findViewById<View>(R.id.card_view) as C

        }
    }
}