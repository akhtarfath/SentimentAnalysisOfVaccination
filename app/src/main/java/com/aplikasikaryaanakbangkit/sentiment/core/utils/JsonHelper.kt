package com.aplikasikaryaanakbangkit.sentiment.core.utils

import android.content.Context
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.CovidStatisticResponse
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(): String? {
        val jsonString: String
        try {
            jsonString = context.resources.openRawResource(R.raw.tourism).bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun loadData(): List<CovidStatisticResponse> {
        val list = ArrayList<CovidStatisticResponse>()
        val responseObject = JSONObject(parsingFileToString().toString())
        val listArray = responseObject.getJSONArray("places")
        for (i in 0 until listArray.length()) {
            val course = listArray.getJSONObject(i)

            val id = course.getString("id")
            val name = course.getString("name")
            val description = course.getString("description")
            val address = course.getString("address")
            val longitude = course.getDouble("longitude")
            val latitude = course.getDouble("latitude")
            val like = course.getInt("like")
            val image = course.getString("image")

            val courseResponse = CovidStatisticResponse(
                id = id,
                name = name,
                description = description,
                address = address,
                longitude = longitude,
                latitude = latitude,
                like = like,
                image = image
            )
            list.add(courseResponse)
        }
        return list
    }
}

