package com.waewaee.themovieapp.data.vos

import com.google.gson.annotations.SerializedName

data class MovieVO(
    @SerializedName("adult")
    val adult: Boolean?,

    @SerializedName("backdrop_path")
    val backDropPath: String?,

    @SerializedName("genre_ids")
    val genreIds: List<Int>?,

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("original_language")
    val originalLanguage: String?,

    @SerializedName("original_title")
    val originalTitle: String?,

    @SerializedName("overview")
    val overView: String?,

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("video")
    val video: Boolean?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("vote_count")
    val voteCount: Int?,

    @SerializedName("belongs_to_collection")
    val belongsToCollection: Boolean?,

    @SerializedName("budget")
    val budget: Int?,

    @SerializedName("homepage")
    val homePage: String?,

    @SerializedName("imdb_id")
    val imdbId: String?,

    @SerializedName("genres")
    val genres: List<GenreVO>?,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyVO>?,

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryVO>?,

    @SerializedName("revenue")
    val revenue: Int,

    @SerializedName("runtime")
    val runtime: Int,

    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageVO>?,

    @SerializedName("status")
    val status: String?,

    @SerializedName("tagline")
    val tagline: String?,


) {
    fun getRatingBasedOnFiveStars(): Float {
        return voteAverage?.div(2)?.toFloat() ?: 0.0f
    }

    fun getGenresAsCommaSeparatedString(): String {
        return genres?.map { it.name }?.joinToString(",") ?: ""
    }

    fun getProductionCountriesAsCommaSeparatedString(): String {
        return productionCountries?.map { it.name }?.joinToString(",") ?: ""
    }
}