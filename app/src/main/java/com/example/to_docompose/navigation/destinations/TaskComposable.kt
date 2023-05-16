package com.example.to_docompose.navigation.destinations

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_docompose.ui.screens.task.TaskScreen
import com.example.to_docompose.ui.viewmodels.SharedViewModel
import com.example.to_docompose.util.Action
import com.example.to_docompose.util.Constants.TASK_ARGUMENT_KEY
import com.example.to_docompose.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit){
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY){
            type = NavType.IntType
        }
    )){ navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        Log.d("NavigationArguments", "arguments from list screen task id" + taskId)
        sharedViewModel.getSelectedTask(taskId = taskId)
        //I observe selected task from the sharedViewModel
        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        //Whenever selectedTask changes 'LaunchedEffect" calls
        LaunchedEffect(key1 = selectedTask) {
            //If FAB clicked or Selected task clicked
            if(selectedTask != null || taskId == -1) {
                sharedViewModel.updateTaskFields(selectedTask)
            }
        }
        TaskScreen(
            selectedTask = selectedTask,
            sharedViewModel = sharedViewModel,
            navigateToListScreen = navigateToListScreen
        )
    }
    Log.d("TaskComposable", "TaskComposable");
}