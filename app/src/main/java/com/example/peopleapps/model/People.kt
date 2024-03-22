package com.example.peopleapps.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class People(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
): Parcelable
