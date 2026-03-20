package com.example.airport.di

import android.content.Context
import com.example.airport.data.AirportDatabase
import com.example.airport.data.dao.AirportDao
import com.example.airport.data.repository.AirportRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * object AppModule
 *
 * Gestiona la inyeccion de dependencias
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): AirportDatabase {
        return AirportDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideDao(database: AirportDatabase): AirportDao {
        return database.getAirportDao()
    }

    @Provides
    @Singleton
    fun provideRepository(airportDao: AirportDao): AirportRepository {
        return AirportRepository(airportDao)
    }
}