package com.example.test.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class PersonModel(val name : String) : Parcelable
