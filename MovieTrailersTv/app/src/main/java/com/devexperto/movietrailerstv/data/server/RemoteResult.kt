package com.devexperto.movietrailerstv.data.server

import com.devexperto.movietrailerstv.domain.Movie
import com.google.gson.annotations.SerializedName

data class RemoteResult(
    val page: Int,
    val results: List<RemoteMovie>
)

data class RemoteMovie(
    val id: Long,
    val name: String,
    val title: String,
    @SerializedName("backgroundPath") val backdropPath: String?,
    @SerializedName("posterPath") val posterPath: String,
    @SerializedName("traillerPath") val trailerPath: String,
    @SerializedName("moviePath") val moviePath: String,
    @SerializedName("overView") val overview: String,
    @SerializedName("releaseDate") val releaseDate: String,
    @SerializedName("genreMovies") val genreIds: List<Int>
)

fun RemoteMovie.toDomain() = Movie(
    title,
    releaseDate,
    overview,
    "http://192.168.1.108:8080/Movies$posterPath",
    backdropPath?.let { "http://192.168.1.108:8080/Movies$backdropPath" } ?: "",
    "http://192.168.1.108:8080/Movies$trailerPath",
    "http://192.168.1.108:8080/Movies$moviePath"
)