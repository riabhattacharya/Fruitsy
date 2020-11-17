package com.newapp.fruitsy.model

import android.os.Parcelable
import com.newapp.fruitsy.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fruit(
    val image: Int? = R.drawable.ic_photo,
    val type: String,
    val price: Float,
    val weight: Float
) : Parcelable

data class FruitList(val fruit: List<Fruit>)