package scomas.se.tinderdog.tasks

import android.os.AsyncTask
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import scomas.se.tinderdog.networks.DogBreedAPIService
import scomas.se.tinderdog.viewmodels.DogViewModel
import java.util.logging.Logger

/**
 * Project : TinderDog
 * Package Name : scomas.se.tinderdog.Tasks
 * Created by svenpettersson on 2019-03-23.
 *
 * Fetches breed information
 */
class DogBreedAsyncTask(val viewModel: DogViewModel,val breed : String) : AsyncTask<Void, Void, String>(){
    val key = "72c31ed9-0c2a-44aa-9c5d-a9e52b7ec921"
    var disposable: Disposable? = null
    val dogBreedAPI = DogBreedAPIService.create("https://api.thedogapi.com/v1/")
    val LOG = Logger.getLogger(this.javaClass.name)

    override fun doInBackground(vararg p0: Void?): String {
        disposable =
                dogBreedAPI.getDogBreed(
                        breed,
                        key
                )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    viewModel.setDogBreed(result)
                                },
                                { error ->
                                    LOG.warning("I cant fetch the dog")
                                    viewModel.fetchDogBreed()
                                }
                        )
        return "ok"
    }
}