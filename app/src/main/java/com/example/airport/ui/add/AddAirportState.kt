package com.example.airport.ui.add

import com.example.airport.data.model.Cities

/**
 * data class AddAirportState
 *
 * Gestiona el estado de las variables que interactuan con la UI
 */
data class AddAirportState(
    val code: String = "",
    val city: Cities = Cities.MALAGA,
    val date: String = "",
    val description: String = "",

    val isError: Boolean = false,
    val titleError: String = "",
    val messageError: String = "",

    val idSelected: Int = 0,
    val isEdition: Boolean = false,

    val showDialog: Boolean = false,

    val isLoading: Boolean = false,

    val onBack: Boolean = false

)