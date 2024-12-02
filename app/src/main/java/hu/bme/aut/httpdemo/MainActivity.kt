package hu.bme.aut.httpdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.httpdemo.data.weather.TripEntry
import hu.bme.aut.httpdemo.ui.screen.MainScreen
import hu.bme.aut.httpdemo.ui.screen.trips.TripsScreen
import hu.bme.aut.httpdemo.ui.theme.WanderLogTheme
import hu.bme.aut.httpdemo.ui.navigation.Screen
import hu.bme.aut.httpdemo.ui.screen.trips.TripDetailsScreen


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WanderLogTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val trips = getDummyTrips() // Provide dummy trip data
                    val navController = rememberNavController()
                    NavGraph(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        trips = trips
                    )
                }
            }
        }
    }

    private fun getDummyTrips(): List<TripEntry> {
        return listOf(
            TripEntry(
                tripId = 1,
                destination = "Paris",
                startDate = "2023-01-01",
                endDate = "2023-01-07"
            ),
            TripEntry(
                tripId = 2,
                destination = "London",
                startDate = "2023-02-10",
                endDate = "2023-02-15"
            )
        )
    }
}

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    trips: List<TripEntry>
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        // Main screen composable
        composable(Screen.Main.route) {
            MainScreen(
                onTripsSelected = { navController.navigate(Screen.Trips.route) },
                onExpensesSelected = { /* Handle navigation */ },
                onPhotosSelected = { /* Handle navigation */ },
                onMapSelected = { /* Handle navigation */ }
            )
        }

        // Trips screen composable
        composable(Screen.Trips.route) {
            TripsScreen(
                trips = trips,
                onAddTrip = { /* Handle Add Trip */ },
                onDeleteTrip = { /* Handle Delete Trip */ },
                onTripSelected = { tripId ->
                    navController.navigate(Screen.TripDetails.createRoute(tripId))
                }
            )
        }

        // Trip details screen composable
        composable(
            route = Screen.TripDetails.route,
            arguments = listOf(
                navArgument("tripId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val tripId = backStackEntry.arguments?.getString("tripId")
            requireNotNull(tripId) { "tripId parameter wasn't passed." }

            TripDetailsScreen(
                tripId = tripId,
                trips = trips,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
