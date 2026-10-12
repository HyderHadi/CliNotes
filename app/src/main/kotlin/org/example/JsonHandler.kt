package org.example

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File


val file = File("notes.json")

fun writeToJson(
    notes: List<Note>
) {
    val gson = GsonBuilder().setPrettyPrinting().create()
    val json = gson.toJson(notes)
    file.writeText(json)
}

fun readFromJson(
    notesJsonFile: File
): List<Note> {
    if(notesJsonFile.length() == 0L) {
        return emptyList<Note>()
    }
    val json = file.readText()
    val type = object : TypeToken<List<Note>>() {}.type
    val notes: List<Note> = Gson().fromJson(json, type)
    Note.updateNextId(notes.maxOfOrNull { it.id } ?: 0L)
    return notes
}