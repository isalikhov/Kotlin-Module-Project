class Menu<T>(private val data: T) {
    private var maxPunkt: Int = 0
    fun show() {
        while (true) {
            var i = 0

            if (data is Storage) {
                maxPunkt = data.body.size + 1
                println("Список архивов:\n0. Создать архив")

                data.body.forEach {
                    i++
                    println("$i. ${it.name}")
                }
            }
            if (data is Archive) {
                maxPunkt = data.body.size + 1
                println("Список заметок:\n0. Создать заметку")
                data.body.forEach {
                    i++
                    println("$i. ${it.name}")
                }
            }
            if (data is Note) {
                maxPunkt = 0
                println(data)
            }
            println("$maxPunkt. Выход")

            println("\nВведите номер команды:")

            val stringCommand = readlnOrNull() ?: ""
            var command: Int = -1
            try {
                command = stringCommand.toInt()
            } catch (e: Exception) {
                println("Ошибка: введите число!")
                continue
            }

            if (command in 0..maxPunkt)
                when (command) {
                    maxPunkt -> break
                    0 -> newItem()
                    else -> {
                        if (data is Storage) {
                            Menu(data.body[command - 1]).show()
                        }
                        if (data is Archive) {
                            Menu(data.body[command - 1]).show()
                        }
                    }
                }
            else {
                println("Ошибка: нет такого пункта в меню!")
            }
        }
    }

    //Функция создания новой записи в списке
    private fun newItem() {
        if (data is Storage) {
            println("Введите название архива:")
            data.body.add(Archive(readlnOrNull() ?: ""))
            println("")
        }
        if (data is Archive) {
            println("Введите название заметки:")
            val sname = readlnOrNull() ?: ""
            println("Введите текст заметки:")
            val stext = readlnOrNull() ?: ""
            data.body.add(Note(sname, stext))
            println("")
        }
    }
}