package com.avicodes.beershop.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "beer_table")
@Parcelize
data class BeerItem(
    @SerializedName("id")
    @PrimaryKey
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image_url")
    val image_url: String,
    @SerializedName("brewers_tips")
    val brewers_tips: String,
    @SerializedName("tagline")
    val tagline: String,
) : Parcelable