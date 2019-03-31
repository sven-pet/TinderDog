package scomas.se.tinderdog.models

/**
 * Project : TinderDog
 * Package Name : scomas.se.tinderdog.adapters
 * Created by svenpettersson on 2019-03-23.
 *
 * Hold information about a dog. This model is created to handle a dog in the app
 *
 */
class DogModel {

    data class Dog(
            var image : String = "",
            var name: String = "",
            var breedName: String  = "",
            var temperament: String  = "",
            var height: String  = "",
            var weight: String = "",
            var age : String = ""
    )
}