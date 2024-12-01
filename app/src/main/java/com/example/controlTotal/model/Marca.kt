package com.example.controlTotal.model

data class Marca(
    val id: Int = 0,         // ID único de la marca (autogenerado en SQLite)
    val rut: String,         // RUT del trabajador
    val nombre: String,      // Nombre del trabajador
    val apellido: String,    // Apellido del trabajador
    val fechaHora: String,   // Fecha y hora del registro
    val tipo: String         // Tipo de marca (Entrada o Salida)
)
