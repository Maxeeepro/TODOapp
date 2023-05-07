package com.example.to_docompose.ui.viewmodels
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_docompose.data.models.Priority
import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.data.repositories.ToDoRepository
import com.example.to_docompose.util.RequestState
import com.example.to_docompose.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: ToDoRepository
    ): ViewModel() {

    val id: MutableState<Int> = mutableStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)

    //Default state for the SearchAppBAr will be closed
    val searchAppBarState: MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)
    val searchTextState: MutableState<String> = mutableStateOf("")

    private val _allTasks = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<ToDoTask>>> = _allTasks
    //Using viewModelScope / Coroutine will be launch if ViewModel is alive and cancelled after ViewModel dead
    fun getAllTasks(){
        _allTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.getAllTasks.collect {
                    _allTasks.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            //TODO investigation 'Not enough information to infer type variable T'
            //_allTasks.value = RequestState.Error(e)
        }
    }
    private val _selectedTask : MutableStateFlow<ToDoTask?> = MutableStateFlow(null)
    val selectedTask: StateFlow<ToDoTask?> = _selectedTask

    fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
          repository.getSelectedTask(taskId).collect { task ->
              _selectedTask.value = task
          }
        }
    }
    //If we navigate throw FAB
    fun updateTaskFields(selectedTask: ToDoTask?) {
       if (selectedTask != null){
           //If we navigate throw selected task
           id.value = selectedTask.id
           title.value = selectedTask.title
           description.value = selectedTask.description
           priority.value = selectedTask.priority
       } else {
           //If we navigate throw FAB
           id.value = 0
           title.value = ""
           description.value = ""
           priority.value = Priority.LOW
       }
    }
}