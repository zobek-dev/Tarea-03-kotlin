package com.example.controlTotal.database

import android.net.Uri

object MarcaContract {
    const val AUTHORITY = "com.controltotal.marcaprovider"
    val BASE_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY")
    const val PATH_MARCA = "Marca"

    object MarcaEntry {
        val CONTENT_URI: Uri = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_MARCA)
        const val TABLE_NAME = "Marca"
        const val COLUMN_ID = "id"
        const val COLUMN_RUT = "rut"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_APELLIDO = "apellido"
        const val COLUMN_FECHA_HORA = "fecha_hora"
        const val COLUMN_TIPO = "tipo"
    }
}