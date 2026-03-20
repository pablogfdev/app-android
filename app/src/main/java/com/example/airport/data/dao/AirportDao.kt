package com.example.airport.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.airport.data.model.Airport
import kotlinx.coroutines.flow.Flow

/**
 * Interface AirportDao
 *
 * Hace de intermediario entre el repositorio y la room
 */
@Dao
interface AirportDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(airport: Airport)

    @Update
    suspend fun update(airport: Airport)

    @Delete
    suspend fun delete(airport: Airport)

    @Query("Select * from airport")
    fun getAllAirports(): Flow<List<Airport>>

    @Query("Select * from airport where _id = :id limit 1")
    suspend fun getAirportById(id: Int): Airport?

    @Query("Select * from airport where code = :code and date = :date limit 1")
    suspend fun getAirportByCodeAndDate(code: String, date: String): Airport?
}