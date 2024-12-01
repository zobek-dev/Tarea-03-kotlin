package com.example.controlTotal.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "Asistencia.db"
        const val DATABASE_VERSION = 1

        const val TABLE_MARCA = "Marca"
        const val COLUMN_ID = "id"
        const val COLUMN_RUT = "rut"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_APELLIDO = "apellido"
        const val COLUMN_FECHA_HORA = "fecha_hora"
        const val COLUMN_TIPO = "tipo"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = """
            CREATE TABLE $TABLE_MARCA (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_RUT TEXT NOT NULL,
                $COLUMN_NOMBRE TEXT NOT NULL,
                $COLUMN_APELLIDO TEXT NOT NULL,
                $COLUMN_FECHA_HORA TEXT NOT NULL,
                $COLUMN_TIPO TEXT NOT NULL
            )
        """
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_MARCA")
        onCreate(db)
    }
}
