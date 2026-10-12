package org.example


import com.google.gson.GsonBuilder

fun writeToJson(
    notes: List<Note>
) {
    val gson = GsonBuilder().setPrettyPrinting().create()
    val json = gson.toJson(notes)
    file.writeText(json)
}
