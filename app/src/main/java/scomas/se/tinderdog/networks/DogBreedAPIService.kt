package scomas.se.tinderdog.networks

import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import scomas.se.tinderdog.models.DogBreedModel

/**
 * Project : TinderDog
 * Package Name : scomas.se.tinderdog.networks
 * Created by svenpettersson on 2019-03-23.
 *
 * Service to connect to https://api.thedogapi.com/v1/breeds/search. This will fetch information on
 * the breed 'q' in Query
 */
interface DogBreedAPIService {

    @GET("breeds/search")
    fun getDogBreed(
            @Query("q") breed: String,
            @Header( "x-api-key") apiKey : String
            ): Observable<List<DogBreedModel.Breed>>



    companion object {
        fun create(url : String): DogBreedAPIService {

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

            return retrofit.create(DogBreedAPIService::class.java)
        }
    }
}