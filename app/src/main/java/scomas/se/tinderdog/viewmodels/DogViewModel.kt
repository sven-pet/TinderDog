package scomas.se.tinderdog.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import scomas.se.tinderdog.models.DogBreedModel
import scomas.se.tinderdog.models.DogImageModel
import scomas.se.tinderdog.models.DogModel
import scomas.se.tinderdog.models.DogNameModel
import scomas.se.tinderdog.tasks.DogBreedAsyncTask
import scomas.se.tinderdog.tasks.DogImageAsyncTask
import scomas.se.tinderdog.tasks.DogNameAsyncTask

/**
 * Project : TinderDog
 * Package Name : scomas.se.tinderdog.viewmodels
 * Created by svenpettersson on 2019-03-23.
 */
class DogViewModel : ViewModel()  {
    var dog:DogModel.Dog = DogModel.Dog()
    var finisheddog:MutableLiveData<Int> = MutableLiveData<Int>()
    var dogs = mutableListOf<DogModel.Dog>()

    fun fetchDogImage(){
        val task = DogImageAsyncTask(this)
        task.execute()
    }

    fun fetchDogBreed(){
        val task = DogBreedAsyncTask(this,"Collie")
        task.execute()
    }

    fun fetchDogName(){
        val task = DogNameAsyncTask(this)
        task.execute()
    }

    fun fetchDog(){
        dog = DogModel.Dog()
        val task = DogNameAsyncTask(this)
        task.execute()
    }

    fun setDogName(dogName : DogNameModel.Name){
        dog.name = dogName.name
        val task = DogImageAsyncTask(this)
        task.execute()
    }

    fun setDogImage(dogImage : DogImageModel.DogImage){

        //https://images.dog.ceo/breeds/pembroke/n02113023_660.jpg
        dog.image = dogImage.message
        var breed = dogImage.message.split("/breeds/")[1]
        breed = breed.split("/")[0]
        dog.breedName = breed
        val task = DogBreedAsyncTask(this, breed)
        task.execute()

    }

    fun setDogBreed(breed : List<DogBreedModel.Breed>){
        if(breed.size < 1) {
            this.fetchDog()
            return
        }
        var dogBreed = breed[0]
        dog.height = getRandomValue(dogBreed.height.metric)
        dog.weight = getRandomValue(dogBreed.weight.metric)
        dog.temperament = dogBreed.temperament
        dog.age = getRandomValue(dogBreed.life_span)
        if(dog.image != "") {
            dogs.add(dog)
            finisheddog.postValue(dogs.size)
        }

        if (dogs.size < 20) {
            this.fetchDog()
        }
    }

    fun removeFirstDog() {
        dogs.remove(dogs[0])
    }

    fun getRandomValue(breedString: String) : String {
        val tempArray = breedString.split('-')
        var maxValue =tempArray[1].replace("[^A-Za-z ]", "")
        maxValue = maxValue.split(' ')[1]
        val maxValueInt = maxValue.toInt()
        val randomValue = (2..maxValueInt).shuffled().first()
        return randomValue.toString()
    }


}