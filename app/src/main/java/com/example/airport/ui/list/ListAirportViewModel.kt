package com.example.airport.ui.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airport.data.model.Airport
import com.example.airport.data.repository.AirportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * class ListAirportViewModel
 *
 * Gestiona el estado y la logica de la UI
 */
@HiltViewModel
class ListAirportViewModel @Inject constructor(
    private val airportRepository: AirportRepository
) : ViewModel() {

    var state by mutableStateOf(ListAirportState())
        private set


    fun getList(){
        state = state.copy(isLoading = true)

        viewModelScope.launch {
            airportRepository.getAllAirports().collect{ airports ->
                state = state.copy(airports = airports, isLoading = false, noData = airports.isEmpty())
            }
        }
    }

    fun onSort(){
        state = state.copy(
            airports = if (state.isSort) state.airports.sortedBy { it.code }
            else state.airports.sortedByDescending { it.code },
            isSort = !state.isSort
        )
    }

    fun onShowDialogValueChange(){
        state = state.copy(isShowDialog = !state.isShowDialog)
    }

    fun onSelectedAirportValueChange(airport: Airport){
        state = state.copy(selectedAirport = airport)
    }

    fun onDelete(){
        viewModelScope.launch { airportRepository.deleteAirport(state.selectedAirport!!) }
        state = state.copy(selectedAirport = null)
    }

}