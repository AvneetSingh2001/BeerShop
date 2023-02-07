package com.avicodes.beershop.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BeerItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image_url")
    val image_url: String,
    @SerializedName("brewers_tips")
    val brewers_tips: String,
    @SerializedName("food_pairing")
    val foodPairing: List<String>,
    @SerializedName("tagline")
    val tagline: String,
) : Parcelable