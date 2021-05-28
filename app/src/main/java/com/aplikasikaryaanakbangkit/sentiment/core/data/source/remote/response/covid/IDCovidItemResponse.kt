package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.covid

import com.google.gson.annotations.SerializedName

data class IDCovidItemResponse(

        @field:SerializedName("countryRegion")
        val countryRegion: String? = null,

        @field:SerializedName("peopleHospitalized")
        val peopleHospitalized: Any? = null,

        @field:SerializedName("active")
        val active: Int? = null,

        @field:SerializedName("fips")
        val fips: Any? = null,

        @field:SerializedName("peopleTested")
        val peopleTested: Any? = null,

        @field:SerializedName("confirmed")
        val confirmed: Int? = null,

        @field:SerializedName("provinceState")
        val provinceState: Any? = null,

        @field:SerializedName("long")
        val jsonMemberLong: Double? = null,

        @field:SerializedName("incidentRate")
        val incidentRate: Double? = null,

        @field:SerializedName("uid")
        val uid: Int? = null,

        @field:SerializedName("recovered")
        val recovered: Int? = null,

        @field:SerializedName("lastUpdate")
        val lastUpdate: Long? = null,

        @field:SerializedName("admin2")
        val admin2: Any? = null,

        @field:SerializedName("combinedKey")
        val combinedKey: String? = null,

        @field:SerializedName("lat")
        val lat: Double? = null,

        @field:SerializedName("deaths")
        val deaths: Int? = null,

        @field:SerializedName("iso3")
        val iso3: String? = null
)