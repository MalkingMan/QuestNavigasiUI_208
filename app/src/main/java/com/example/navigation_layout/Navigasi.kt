package com.example.navigation_layout

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


enum class Navigasi {
    Formulir,
    Detail
}

@Composable
fun DataApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier

) {
    Scaffold { isiRuang ->
        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulir.name,

            modifier = Modifier.padding(paddingValues = isiRuang)){
            composable(route = Navigasi.Formulir.name){
                FormIsian(
                    onSubmitBtnClick = {
                        navController.navigate(Navigasi.Detail.name)
                    }
                )
            }
            composable(route = Navigasi.Detail.name){
                TampilData(
                    onbackBtnClick = {cancelAndBackToFormulir(navController)}
                )
            }
        }

    }
}