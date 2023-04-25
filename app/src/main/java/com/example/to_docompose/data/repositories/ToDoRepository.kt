package com.example.to_docompose.data.repositories

import com.example.to_docompose.data.ToDoDAO
import com.example.to_docompose.data.models.ToDoTask
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped //means that instance of the ToDoRepository will be alive as ViewModel
class ToDoRepository @Inject constructor(private val toDoDAO: ToDoDAO) {
    val getAllTasks: Flow<List<ToDoTask>> = toDoDAO.getAllTasks()
    val sortByLowPriority: Flow<List<ToDoTask>> = toDoDAO.sortByLowPriority()
    val sortByHighPriority: Flow<List<ToDoTask>> = toDoDAO.sortByLowPriority()

    fun getSelectedTask(taskId: Int): Flow<ToDoTask> {
        return toDoDAO.getSelectedTask(taskId = taskId)
    }

    suspend fun addTask(toDoTask: ToDoTask) {
        toDoDAO.addTask(toDoTask = toDoTask)
    }

    suspend fun updateTask(toDoTask: ToDoTask) {
        toDoDAO.updateTask(toDoTask = toDoTask)
    }

    suspend fun deleteTask(toDoTask: ToDoTask) {
        toDoDAO.deleteTask(toDoTask = toDoTask)
    }

    suspend fun deleteAllTask() {
        toDoDAO.deleteAllTasks()
    }

    fun searchDataBase(searchQuery: String) : Flow<List<ToDoTask>>{
        return toDoDAO.searchDatabase(searchQuery = searchQuery)
    }

}