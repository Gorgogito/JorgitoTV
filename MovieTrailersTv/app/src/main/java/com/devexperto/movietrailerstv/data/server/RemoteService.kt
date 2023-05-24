package com.devexperto.movietrailerstv.data.server

import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {

    //@GET("discover/movie")
    @GET("Apis/Api/v1/Movie")
    suspend fun listPopularMovies(
        @Query("filter_genre") filterGenre: Long,
        @Query("order_date") orderDate: String
    ): RemoteResult

}