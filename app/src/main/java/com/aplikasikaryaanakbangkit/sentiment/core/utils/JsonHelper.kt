package com.aplikasikaryaanakbangkit.sentiment.core.utils

import android.content.Context
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.teams.TeamsResponse
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingDevelopersFileToString(): String? {
        val jsonString: String
        try {
            jsonString = context.resources.openRawResource(R.raw.developers).bufferedReader()
                    .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun loadDataTeams(): List<TeamsResponse> {
        val list = ArrayList<TeamsResponse>()
        val responseObject = JSONObject(parsingDevelopersFileToString().toString())
        val listArray = responseObject.getJSONArray("members")
        for (i in 0 until listArray.length()) {
            val teams = listArray.getJSONObject(i)

            val id = teams.getString("id")
            val name = teams.getString("name")
            val urlPicture = teams.getString("urlPicture")

            val teamsResponse = TeamsResponse(
                    id,
                    name,
                    urlPicture
            )
            list.add(teamsResponse)
        }
        return list
    }
}

