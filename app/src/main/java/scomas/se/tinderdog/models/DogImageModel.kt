package scomas.se.tinderdog.models

/**
 * Project : TinderDog
 * Package Name : scomas.se.tinderdog.adapters
 * Created by svenpettersson on 2019-03-23.
 *
 * Hold information about dog image
 *
 */
class DogImageModel {

    data class DogImage(
            val status : String,
            val message : String
    )
}