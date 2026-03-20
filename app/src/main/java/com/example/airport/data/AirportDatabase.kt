package com.example.airport.data;

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.airport.data.converter.EnumConverter
import com.example.airport.data.dao.AirportDao
import com.example.airport.data.model.Airport
import com.example.airport.data.model.Cities
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.Executors

@Database(version = 1, entities = [Airport::class])
@TypeConverters(EnumConverter::class)
abstract class AirportDatabase : RoomDatabase() {

    abstract fun getAirportDao(): AirportDao

    companion object {
        @Volatile
        private var INSTANCE: AirportDatabase? = null

        fun getDatabase(context: Context): AirportDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AirportDatabase::class.java,
                    "airport_database11.db"
                )
                    // Callback para pre-poblar la base de datos
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Se utiliza un executor para realizar la inserción en un hilo de fondo
                            //Las tareas se ejecutan de forma secuencial en un hilo/s
                            Executors.newSingleThreadExecutor().execute {
                                INSTANCE?.let { database ->
                                    init(database)
                                }
                            }
                        }

                        fun init(database: AirportDatabase){
                            val dao = database.getAirportDao()

                            runBlocking {
                                dao.insert(
                                    Airport(
                                        code = "AED",
                                        city = Cities.MALAGA,
                                        date = "12/10/2024",
                                        description = "Esto es una descripión"
                                    )
                                )
                                dao.insert(
                                    Airport(
                                        code = "GFA",
                                        city = Cities.SEVILLA,
                                        date = "12/10/2024",
                                        description = "Esto es una descripión"
                                    )
                                )
                                dao.insert(
                                    Airport(
                                        code = "TAG",
                                        city = Cities.CORDOBA,
                                        date = "12/10/2024",
                                        description = "Esto es una descripión"
                                    )
                                )
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}

