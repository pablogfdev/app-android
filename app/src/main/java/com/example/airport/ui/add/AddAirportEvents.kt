package com.example.airport.ui.add

/**
 * data class AddAirportEvents
 *
 * Gestiona las funciones del viewmodel en la Screen de una forma mucho mas controlada
 */
data class AddAirportEvents(
    val onSave: () -> Unit,
    val onCodeValueChange: (String) -> Unit,
    val onCityValueChange: (String) -> Unit,
    val onDateValueChange: (String) -> Unit,
    val onDescriptionValueChange: (String) -> Unit,
    val onShowDialogValueChange: () -> Unit,
    val onErrorValueChange: () -> Unit
)
