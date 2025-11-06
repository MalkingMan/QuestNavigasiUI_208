package com.example.navigation_layout

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation_layout.view.FormIsian
import com.example.navigation_layout.view.TampilData

enum class Navigasi {
    Formulir,
    Detail
}

@Composable
fun DataApp(
    navController: NavHostController = rememberNavController()
) {
    // 1. PERBAIKAN: Ubah variabel biasa menjadi state agar Compose dapat melacak perubahan.
    var nama by remember { mutableStateOf("") }
    var jenisKelamin by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }

    Scaffold { isiRuang ->
        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulir.name,
            modifier = Modifier.padding(isiRuang)
        ) {
            composable(route = Navigasi.Formulir.name) {
                FormIsian(
                    // 2. PERBAIKAN: Ganti nama parameter menjadi 'onSubmitBtnClick'.
                    onSubmitBtnClick = { n, jk, a ->
                        // Sekarang, perubahan nilai akan disimpan ke dalam state
                        nama = n
                        jenisKelamin = jk
                        alamat = a
                        navController.navigate(Navigasi.Detail.name)
                    }
                )
            }
            composable(route = Navigasi.Detail.name) {
                // TampilData akan secara otomatis diperbarui karena 'nama', 'jenisKelamin', dan 'alamat' adalah state.
                TampilData(
                    nama = nama,
                    jenisKelamin = jenisKelamin,
                    alamat = alamat,
                    // 3. PERBAIKAN: Samakan nama parameter menjadi 'onbackBtnClick'.
                    onbackBtnClick = {
                        cancelAndBackToFormulir(navController)
                    }
                )
            }
        }
    }
}

private fun cancelAndBackToFormulir(navController: NavHostController) {
    // 4. PERBAIKAN: Sederhanakan navigasi kembali ke layar sebelumnya.
    navController.popBackStack()
}
