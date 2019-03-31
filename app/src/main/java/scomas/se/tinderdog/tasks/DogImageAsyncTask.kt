package scomas.se.tinderdog.tasks

import android.os.AsyncTask
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import scomas.se.tinderdog.networks.DogImageAPIService
import scomas.se.tinderdog.viewmodels.DogViewModel
import java.util.logging.Logger

/**
 * Project : TinderDog
 * Package Name : scomas.se.tinderdog.Tasks
 * Created by svenpettersson on 2019-03-23.
 *
 * Fetches dog image
 *
 */
class DogImageAsyncTask(val viewModel: DogViewModel) : AsyncTask<Void, Void, String>(){

    var disposable: Disposable? = null
    val dogAPI = DogImageAPIService.create("https://dog.ceo/api/")
    val Tag = "SpotifyConnectAsyncTask"
    val LOG = Logger.getLogger(this.javaClass.name)

    override fun doInBackground(vararg p0: Void?): String {
        disposable =
                dogAPI.getDog()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    viewModel.setDogImage(result)
                                },
                                { error ->
                                    LOG.warning("I cant fetch the dog")
                                    viewModel.fetchDogImage()
                                }
                        )
        return "ok"
    }
}