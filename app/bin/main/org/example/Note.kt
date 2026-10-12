package org.example


data class Note(
    val id: Long = nextId++,
    val title: String,
    val text: String
) {

    companion object {
        private var nextId = 1L

        fun updateNextId(currentId: Long) {
            if (currentId >= nextId) {
                nextId = currentId + 1
            }
        }
    }
}
