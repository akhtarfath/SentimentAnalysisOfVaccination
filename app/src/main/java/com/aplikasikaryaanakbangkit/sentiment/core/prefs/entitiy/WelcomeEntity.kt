package com.aplikasikaryaanakbangkit.sentiment.core.prefs.entitiy

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WelcomeEntity(
        var isActive: Boolean? = false,
) : Parcelable