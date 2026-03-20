package com.example.airport.ui.list

import com.example.airport.data.model.Airport

/**
 * data class ListAirportState
 *
 * Gestiona el estado de las variables que interactuan con la UI
 */
data class ListAirportState(
    val noData: Boolean = false,
    val isLoading: Boolean = false,
    val airports: List<Airport> = emptyList(),

    val isSort: Boolean = false,
    val isShowDialog: Boolean = false,
    val selectedAirport: Airport? = null
)