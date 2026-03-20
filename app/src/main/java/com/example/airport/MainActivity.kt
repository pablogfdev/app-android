package com.example.airport

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.airport.home.AirportGraph
import com.example.airport.home.airportGraph
import com.example.airport.ui.theme.BreakdownAppKotlinTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * class MainActivity
 *
 * clase principal
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BreakdownAppKotlinTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = AirportGraph.ROUTE
                ) {
                    airportGraph(navController = navController)
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BreakdownAppKotlinTheme {
        Greeting("Android")
    }
}