package com.example.finalproject1_11.Model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class ArgsLocation (
    val lati : Double,
    val longi : Double
        ):Parcelable