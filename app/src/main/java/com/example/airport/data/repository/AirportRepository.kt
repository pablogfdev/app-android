package com.example.airport.data.repository

import com.example.airport.data.dao.AirportDao
import com.example.airport.data.model.Airport
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * class AirportRepository
 *
 * Accede a la room mendiante el Dao para realizar diversas operaciones
 * De esta manera se consigue desde el ViewModel acceder a la room
 */
class AirportRepository @Inject constructor(
    private val airportDao: AirportDao
) {

    suspend fun insertAirport(airport: Airport){
        airportDao.insert(airport)
    }

    suspend fun updateAirport(airport: Airport){
        airportDao.update(airport)
    }

    suspend fun deleteAirport(airport: Airport){
        airportDao.delete(airport)
    }

    suspend fun getAirportById(id: Int): Airport?{
        return airportDao.getAirportById(id)
    }

    fun getAllAirports(): Flow<List<Airport>>{
        return airportDao.getAllAirports()
    }

    suspend fun getAirportByCodeAndDate(code: String, date: String): Airport?{
        return airportDao.getAirportByCodeAndDate(code, date)
    }

    suspend fun existAirport(code: String, date: String): Boolean{
        return getAirportByCodeAndDate(code, date) != null
    }
}