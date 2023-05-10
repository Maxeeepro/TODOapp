package com.example.to_docompose.util

object Constants {
    //database
    const val DATABASE_TABLE = "todo_table"
    const val DATABASE_NAME = "todo_database"

    //navigation routes
    //route name/{argument key name}
    const val LIST_SCREEN = "list/{action}" //action key of the argument
    const val TASK_SCREEN = "task/{taskId}"

    //names keys arguments
    const val LIST_ARGUMENT_KEY = "action"
    const val TASK_ARGUMENT_KEY = "taskId"

    const val MAX_TITLE_LENGTH = 20
}