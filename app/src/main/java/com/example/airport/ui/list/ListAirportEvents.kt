package com.example.airport.ui.list

import com.example.airport.data.model.Airport

/**
 * data class ListAirportEvents
 *
 * Gestiona las funciones del viewmodel en la Screen de una forma mucho mas controlada
 */
data class ListAirportEvents(
    val onSort: () -> Unit,
    val onShowDialogValueChange: () -> Unit,
    val onDelete: () -> Unit,
    val onSelectedAirportValueChange: (Airport) -> Unit

)