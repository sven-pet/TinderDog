package scomas.se.tinderdog.tasks

import android.os.AsyncTask
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import scomas.se.tinderdog.networks.DogNameAPIService
import scomas.se.tinderdog.viewmodels.DogViewModel
import java.util.logging.Logger

/**
 * Project : TinderDog
 * Package Name : scomas.se.tinderdog.Tasks
 * Created by svenpettersson on 2019-03-23.
 *
 * Fetches dog name
 *
 */
class DogNameAsyncTask(val viewModel : DogViewModel) : AsyncTask<Void, Void, String>(){

    var disposable: Disposable? = null
    val dogAPI = DogNameAPIService.create("https://uinames.com/")
    val Tag = "SpotifyConnectAsyncTask"
    val LOG = Logger.getLogger(this.javaClass.name)

    override fun doInBackground(vararg p0: Void?): String {
        disposable =
                dogAPI.getDogName(
                        "sweden"
                )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                   viewModel.setDogName(result)
                                },
                                { error ->
                                    LOG.warning("I cant fetch the dog")
                                    viewModel.fetchDogName()
                                }
                        )
        return "ok"
    }
}