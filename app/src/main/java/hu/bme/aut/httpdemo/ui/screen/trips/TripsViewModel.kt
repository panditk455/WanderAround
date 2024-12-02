package hu.bme.aut.httpdemo.ui.screen.trips

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.httpdemo.data.weather.TripEntry
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripViewModel @Inject constructor() : ViewModel() {

    var tripUiState: TripUiState by mutableStateOf(TripUiState.Loading)
    private val trips = mutableListOf<TripEntry>()

    init {
        refreshAllTrips()
    }

    fun refreshAllTrips() {
        tripUiState = TripUiState.Loading
        viewModelScope.launch {
            try {
                // Simulating fetching trips (replace with real database/API call)
                tripUiState = TripUiState.Success(trips.toList())
            } catch (e: Exception) {
                tripUiState = TripUiState.Error
            }
        }
    }

    fun addTrip(trip: TripEntry) {
        viewModelScope.launch {
            trips.add(trip)
            refreshAllTrips()
        }
    }

    fun removeTrip(tripId: Int) {
        viewModelScope.launch {
            trips.removeIf { it.tripId == tripId }
            refreshAllTrips()
        }
    }

    fun fetchTripDetails(tripId: Int) {
        viewModelScope.launch {
            tripUiState = TripUiState.Loading
            try {
                val trip = trips.firstOrNull { it.tripId == tripId }
                trip?.let {
                    tripUiState = TripUiState.Success(listOf(it))
                } ?: run {
                    tripUiState = TripUiState.Error
                }
            } catch (e: Exception) {
                tripUiState = TripUiState.Error
            }
        }
    }
}

sealed interface TripUiState {
    data class Success(val trips: List<TripEntry>) : TripUiState
    object Error : TripUiState
    object Loading : TripUiState
}
