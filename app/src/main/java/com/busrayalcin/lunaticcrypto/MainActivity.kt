package com.busrayalcin.lunaticcrypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.busrayalcin.lunaticcrypto.ui.theme.LunaticCryptoTheme
import com.busrayalcin.lunaticcrypto.view.CryptoDetailScreen
import com.busrayalcin.lunaticcrypto.view.CryptoListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LunaticCryptoTheme {
               val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "crypto_list_screen"){
                    composable("crypto_list_screen"){
                        CryptoListScreen(navController = navController)
                        //CryptoListScreen
                    }

                    composable("crypto_detail_screen/{cryptoId}/{cryptoPrice}", arguments = listOf(
                        navArgument("cryptoId"){
                            type = NavType.StringType
                        },
                        navArgument("cryptoPrice"){
                            type = NavType.StringType
                        }
                    )){
                        val cryptoId = remember {
                            it.arguments?.getString("cryptoId")
                        }
                        val cryptoPrice = remember {
                            it.arguments?.getString("cryptoPrice")
                        }
                        CryptoDetailScreen(id = cryptoId ?: "",
                            price = cryptoPrice ?: "" ,
                            navController = navController)
                        //CryptoDetailScreen
                    }
                }
            }
        }
    }
}