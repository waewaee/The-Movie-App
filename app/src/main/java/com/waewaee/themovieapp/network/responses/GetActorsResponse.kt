package com.waewaee.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.waewaee.themovieapp.data.vos.ActorVO

data class GetActorsResponse(
    @SerializedName("results")
    val results: List<ActorVO>?

)
