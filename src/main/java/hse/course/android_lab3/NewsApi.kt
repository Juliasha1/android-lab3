package hse.course.android_lab3

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("api/1/news")
    fun get(
        @Query("apikey") apikey: String,
        @Query("q") q: String,
        @Query("language") language: String
    ): Call<Result>

    companion object Factory {

        fun create(): NewsApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://newsdata.io/").build()

            return retrofit.create(NewsApi::class.java)
        }
    }
}