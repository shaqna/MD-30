package com.example.peopleapps.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class People(
    val id: Int,
    val email: String,

    @field:Json(name = "first_name")
    val firstName: String,

    @field:Json(name = "last_name")
    val lastName: String,
    val avatar: String
): Parcelable
