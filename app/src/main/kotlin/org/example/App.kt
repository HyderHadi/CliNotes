package org.example


val notes = mutableListOf<Note>()

fun main() {

    var command: String? = null
    file.createNewFile()

    notes.addAll(readFromJson(file))
    while (command?.lowercase() != "exit") {

        println(
            """

            (((( Cli Notes ))))
            1 - create
            2 - edit
            3 - list
            4 - delete
            5 - search
            6 - exit
            """.trimIndent()
        )
        print("type command(case insensitive): ")
        command = readln()
        when (command) {
            "create" -> {
                print("Title of Note: ")
                val title = readln().ifBlank { "Untitled" }
                print("Write Note: ")
                val text = readln()
                notes.add(
                    Note(
                        title = title,
                        text = text
                    )
                )
                writeToJson(notes)
                println("Note Successfully Created\n")
            }

            "edit" -> {
                print("Type id of note you want to edit: ")
                val id = readln().toLongOrNull()
                val tmpNoteIndex = notes.indexOfFirst { it.id == id }
                if (tmpNoteIndex == -1) {
                    println("Note Doesn't Exist\n")
                    continue
                }
                println("You are editing (title: ${notes[tmpNoteIndex].title}, id: ${notes[tmpNoteIndex].id})")
                println("-------------------------------------------------------------")
                println("Note's Body:-")
                println(notes[tmpNoteIndex].text)
                println("-------------------------------------------------------------")
                print("Title of the note: ")
                val newTitle = readln().ifBlank { "Untitled" }
                print("Body of Note: ")
                val newText = readln()
                notes[tmpNoteIndex] = notes[tmpNoteIndex].copy(
                    title = newTitle,
                    text = newText
                )
                writeToJson(notes)
                println("Note Successfully Edited\n")
            }

            "list" -> {
                println("-------------------------------------------------------------")
                notes.forEach { note ->
                    println("ID: ${note.id}, Title: ${note.title}")
                    println("Note's Body:-")
                    println(note.text)
                    println("-------------------------------------------------------------")
                }
            }
            "delete" -> {
                print("Type note's id to delete: ")
                val tmpId = readln().toLongOrNull()
                if(!notes.any { it.id == tmpId}) {
                    println("Note Doesn't Exist\n")
                    continue
                }
                notes.removeAll { it.id == tmpId }
                writeToJson(notes)
                println("Note Deleted Successfully\n")
            }
            
            "search" -> {
                print("Search a note by title: ")
                val tmpTitle = readln()
                val searchResults = notes.filter { 
                    it.title.contains(tmpTitle, ignoreCase = true)
                }
                if(searchResults.isEmpty()) {
                    println("No Matching Results\n")
                    continue
                }
                println("-------------------------------------------------------------")
                searchResults.forEach { 
                    println("ID: ${it.id}, Title: ${it.title}\n")
                    println("Note's Body:-")
                    println(it.text)
                    println("-------------------------------------------------------------")
                }
            }
        }
    }
}
