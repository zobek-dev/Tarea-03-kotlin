package com.example.controlTotal.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private const val DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss" // Formato para fecha y hora
    private const val DATE_FORMAT = "yyyy-MM-dd" // Formato solo para fecha
    private const val TIME_FORMAT = "HH:mm:ss" // Formato solo para hora

    // Obtiene la fecha y hora actual en formato "yyyy-MM-dd HH:mm:ss"
    fun getCurrentDateTime(): String {
        val currentDateTime = Date()
        val formatter = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault())
        return formatter.format(currentDateTime)
    }

    // Obtiene la fecha actual en formato "yyyy-MM-dd"
    fun getCurrentDate(): String {
        val currentDate = Date()
        val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        return formatter.format(currentDate)
    }

    // Obtiene la hora actual en formato "HH:mm:ss"
    fun getCurrentTime(): String {
        val currentTime = Date()
        val formatter = SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
        return formatter.format(currentTime)
    }

    // Convierte una cadena de fecha y hora en un objeto Date
    fun parseDateTime(dateTime: String): Date? {
        val formatter = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault())
        return try {
            formatter.parse(dateTime)
        } catch (e: Exception) {
            null
        }
    }

    // Formatea un objeto Date a una cadena con el formato "yyyy-MM-dd HH:mm:ss"
    fun formatDateTime(date: Date): String {
        val formatter = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault())
        return formatter.format(date)
    }
}
