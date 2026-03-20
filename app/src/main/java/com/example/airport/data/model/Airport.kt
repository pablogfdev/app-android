package com.example.airport.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Cities{
    MALAGA, SEVILLA, CORDOBA
}

/**
 * Data class Airport
 *
 * Almacena las variables que se manejan a lo largo de la aplicacion,
 * al mismo tiempo funciona como tabla en room
 */
@Entity
data class Airport(
    @PrimaryKey(autoGenerate = true) val _id: Int = 0,
    val code: String,
    val city: Cities,
    val date: String,
    val description: String
)