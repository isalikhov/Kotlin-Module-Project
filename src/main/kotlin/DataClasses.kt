//Дата-класс Заметка
data class Note(val name: String, var body: String = "") {
    override fun toString(): String {
        return ("Имя заметки: $name \nТекст заметки: $body")
    }
}

//Дата-класс Архив - список заметок
data class Archive(val name: String) {
    var body = mutableListOf<Note>()
}

//Дата-класс Накопитель - список архивов
data class Storage(val name: String) {
    var body = mutableListOf<Archive>()
}
