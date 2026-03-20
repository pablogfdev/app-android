package com.example.airport.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.airport.ui.add.AddAirportScreen
import com.example.airport.ui.add.AddAirportViewModel
import com.example.airport.ui.list.ListAirportScreen
import com.example.airport.ui.list.ListAirportViewModel


object AirportGraph {
    const val ROUTE = "airport"
    fun listAirport() = "$ROUTE/listAirport"
    fun addAirport(id: String) = "$ROUTE/addAirport/$id"
    fun permissions() = "$ROUTE/permissions"
}

/**
 * fun NavGraphBuilder.airportGraph()
 *
 * Necesaria para navegar por la aplicacion
 *
 * UI a las que navegar:
 *      addAirport
 *      listAirport
 *      permissions
 */
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.airportGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = AirportGraph.permissions(),
        route = AirportGraph.ROUTE
    ) {
        listAirportNav(navController)
        addAirportNav(navController)
        permissionsNav(navController)
    }
}

private fun NavGraphBuilder.listAirportNav(navController: NavController) {
    composable(route = AirportGraph.listAirport()) {
        ListAirportScreen(
            hiltViewModel<ListAirportViewModel>(),
            gotoAdd = { airportId -> navController.navigate(AirportGraph.addAirport(airportId))}
        )
    }
}

private fun NavGraphBuilder.addAirportNav(navController: NavController) {
    composable(
        route = AirportGraph.addAirport("{id}"),
        arguments = listOf(navArgument("id") { type = NavType.IntType })
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getInt("id") ?: 0

        AddAirportScreen(
            id,
            goToList = { navController.navigate(AirportGraph.listAirport()) },
            hiltViewModel<AddAirportViewModel>()
        )
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
private fun NavGraphBuilder.permissionsNav(navController: NavController){
    composable(route = AirportGraph.permissions()){
        PermissionScreen(
            goToList = {navController.navigate(AirportGraph.listAirport())}
        )
    }
}