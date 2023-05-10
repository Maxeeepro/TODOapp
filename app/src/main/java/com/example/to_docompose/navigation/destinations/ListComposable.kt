package com.example.to_docompose.navigation.destinations

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_docompose.ui.screens.list.ListScreen
import com.example.to_docompose.ui.viewmodels.SharedViewModel
import com.example.to_docompose.util.Constants.LIST_ARGUMENT_KEY
import com.example.to_docompose.util.Constants.LIST_SCREEN
import com.example.to_docompose.util.toAction

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
    )
{
    composable(
        route = LIST_SCREEN,
        // Action which sent from the Task Composable
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY){
            type = NavType.StringType
        })
    ){ navBackStackEntry ->
        //by default action is Action.NO_ACTION
        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()
        Log.d("NavigationArguments", "arguments from task screen action name " + action.name)

        //Whenever action value changes this effect will be launched, action value changes after navigation
        //For instance I will receive here Action.UPDATE after user will try to update task
        //I write this value to the action value in the sharedViewModel
        LaunchedEffect(key1 = action) {
            sharedViewModel.action.value = action
        }
        //here composable content
        //set lambda "navigateToTaskScreen" as a parameter
        ListScreen(
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel
        )
    }
}
