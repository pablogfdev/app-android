package com.example.airport.utils

// Importaciones de kotlinx.datetime (Kotlin Multiplatform)
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.toKotlinInstant

// Importaciones de java.time para parsear la fecha
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.LocalDate as JavaLocalDate
import java.time.LocalDateTime as JavaLocalDateTime
import java.time.ZoneOffset

object DateUtils {
    fun now() = Clock.System.now()

    fun Instant.plusDays(days: Int) = this.plus(days, DateTimeUnit.DAY, TimeZone.UTC)

    fun parse(dateString: String): JavaLocalDateTime {
        var properString = dateString

        if (dateString.contains('-')) {
            properString = dateString.replace('-', '/')
        }

        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return JavaLocalDate.parse(properString, formatter).atTime(LocalTime.now())
    }


    fun JavaLocalDateTime.toKotlinInstant(): Instant {
        return toInstant(ZoneOffset.UTC).toKotlinInstant()
    }

    fun nowX(): Instant {
        val timeZone = TimeZone.UTC
        val now = Clock.System.now().toLocalDateTime(timeZone)

        return with(now) {
            LocalDateTime(
                year, month, dayOfMonth, hour, minute, second
            ).toInstant(timeZone)
        }
    }
}