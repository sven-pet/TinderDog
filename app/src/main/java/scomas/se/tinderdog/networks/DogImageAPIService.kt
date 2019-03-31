package scomas.se.tinderdog.networks

import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import scomas.se.tinderdog.models.DogImageModel

/**
 * Project : TinderDog
 * Package Name : scomas.se.tinderdog.networks
 * Created by svenpettersson on 2019-03-23.
 *
 * Service to connect to https://dog.ceo/api/breeds/image/random. This will fetch a new image including
 * the breed in the response
 */
interface DogImageAPIService {

    @GET("breeds/image/random")
    fun getDog(
    ): Observable<DogImageModel.DogImage>



    companion object {
        fun create(url : String): DogImageAPIService {

            val logging = HttpLoggingInterceptor()
            // set your desired log level

            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
            // add your other interceptors …

            // add logging as last interceptor
            httpClient.addInterceptor(logging)

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(url)
                    .client(httpClient.build())
                    .build()

            return retrofit.create(DogImageAPIService::class.java)
        }
    }
}