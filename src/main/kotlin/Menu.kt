class Menu<T : Content>(private val data: T) {
    private var maxPunkt: Int = 0
    private fun <T> MutableList<T>.printList() {
        var i = 0
        this.forEach {
            println("${++i}. $it")
        }
    }

    fun show() {
        while (true) {
            when (data) {
                is Content.Storage -> {
                    maxPunkt = data.body.size + 1
                    println("Список архивов:\n0. Создать архив")
                    data.body.printList()
                }
                is Content.Archive -> {
                    maxPunkt = data.body.size + 1
                    println("Список заметок:\n0. Создать заметку")
                    data.body.printList()
                }
                is Content.Note -> {
                    maxPunkt = 0
                    data.printNote()
                }
            }
            println("$maxPunkt. Выход")

            println("\nВведите номер команды:")
            val stringCommand = readlnOrNull() ?: ""
            var command: Int
            try {
                command = stringCommand.toInt()
            } catch (e: Exception) {
                println("Ошибка: введите число!")
                continue
            }

            when {
                command == maxPunkt -> break
                command == 0 -> newItem()
                (command < 0) or (command > maxPunkt) -> println("Ошибка: нет такого пункта в меню!")
                data is Content.Storage -> Menu(data.body[command - 1]).show()
                data is Content.Archive -> Menu(data.body[command - 1]).show()
            }
        }
    }

    //Функция создания новой записи в списке
    private fun newItem() {
        fun readlnNotNull(text: String): String {
            var s: String
            do {
                println(text)
                s = readlnOrNull() ?: ""
                if (s == "") println("Ошибка: пустой ввод!")
            } while (s == "")
            println()
            return s
        }
        when (data) {
            is Content.Storage -> data.body.add(Content.Archive(readlnNotNull("Введите название архива:")))
            is Content.Archive -> data.body.add(Content.Note(readlnNotNull("Введите название заметки:"), readlnNotNull("Введите текст заметки:")))
        }
    }
}