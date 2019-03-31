package scomas.se.tinderdog.models

/**
 * Project : TinderDog
 * Package Name : scomas.se.tinderdog.adapters
 * Created by svenpettersson on 2019-03-23.
 *
 * Hold information about breed
 *
 */

class DogBreedModel {

    data class Breed(
            val bred_for: String,
            val breed_group: String,
            val id: Int,
            val life_span: String,
            val name: String,
            val origin: String,
            val temperament: String,
            val height: Height,
            val weight: Weight
    )

    data class Height(
            val imperial: String,
            val metric: String
    )

    data class Weight(
            val imperial: String,
            val metric: String
    )
}