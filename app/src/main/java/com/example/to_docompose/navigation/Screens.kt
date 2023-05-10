package com.example.to_docompose.navigation

import androidx.navigation.NavHostController
import com.example.to_docompose.util.Action
import com.example.to_docompose.util.Constants.LIST_SCREEN

class Screens (navController: NavHostController){
    val list: (Action) -> Unit = { action ->
        //action name is argument for sending from task screen to the list screen
        //action from the utils
        navController.navigate(route = "list/${action.name}") {
            popUpTo(LIST_SCREEN) {inclusive = true}
        }
    }
    val task: (Int) -> Unit = { taskId ->
        //task id name is argument from
        navController.navigate(route = "task/$taskId")
    }
}