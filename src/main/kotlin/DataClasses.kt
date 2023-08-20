sealed interface Content {
    //Дата-класс Заметка
    data class Note(val name: String, val body: String) : Content {
        override fun toString() = name
        fun printNote() = println("Имя заметки: $name \nТекст заметки: $body")
    }

    //Дата-класс Архив - список заметок
    data class Archive(val name: String) : Content {
        val body = mutableListOf<Note>()
        override fun toString() = name
    }

    //Дата-класс Накопитель - список архивов
    data class Storage(val name: String) : Content {
        val body = mutableListOf<Archive>()
        override fun toString() = name
    }
}