package hu.bme.aut.httpdemo.ui.navigation


sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Trips : Screen("trips")
    object TripDetails : Screen("tripDetails/{tripId}") {
        fun createRoute(tripId: String) = "tripDetails/$tripId"
    }
}






