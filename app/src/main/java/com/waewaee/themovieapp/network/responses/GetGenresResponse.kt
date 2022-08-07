package com.waewaee.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.waewaee.themovieapp.data.vos.GenreVO

data class GetGenresResponse(
    @SerializedName("genres")
    val genres: List<GenreVO>?
)