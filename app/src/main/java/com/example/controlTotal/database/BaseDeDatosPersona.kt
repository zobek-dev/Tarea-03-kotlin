package com.example.controlTotal.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.controlTotal.model.Marca

class BaseDeDatosPersona(context: Context) {

    private val dbHelper = DatabaseHelper(context)

    /**
     * Inserta un nuevo registro de asistencia en la base de datos.
     *
     * @param marca Objeto `Marca` con los datos a guardar.
     * @return El ID del registro insertado, o -1 si ocurre un error.
     */
    fun insertarMarca(marca: Marca): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_RUT, marca.rut)
            put(DatabaseHelper.COLUMN_NOMBRE, marca.nombre)
            put(DatabaseHelper.COLUMN_APELLIDO, marca.apellido)
            put(DatabaseHelper.COLUMN_FECHA_HORA, marca.fechaHora)
            put(DatabaseHelper.COLUMN_TIPO, marca.tipo)
        }
        return db.insert(DatabaseHelper.TABLE_MARCA, null, values)
    }

    /**
     * Obtiene todos los registros de asistencia almacenados en la base de datos.
     *
     * @return Una lista de objetos `Marca` con todos los registros.
     */
    fun obtenerTodasLasMarcas(): List<Marca> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            DatabaseHelper.TABLE_MARCA, // Tabla
            null, // Columnas (null = todas las columnas)
            null, // Condición WHERE
            null, // Argumentos de la condición WHERE
            null, // GROUP BY
            null, // HAVING
            "${DatabaseHelper.COLUMN_FECHA_HORA} DESC" // Ordenar por fecha/hora descendente
        )
        val marcas = mutableListOf<Marca>()

        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID))
                val rut = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_RUT))
                val nombre = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_NOMBRE))
                val apellido = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_APELLIDO))
                val fechaHora = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_FECHA_HORA))
                val tipo = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_TIPO))
                marcas.add(Marca(id, rut, nombre, apellido, fechaHora, tipo))
            }
        }
        cursor.close()
        return marcas
    }

    /**
     * Elimina un registro específico de asistencia por su ID.
     *
     * @param id El ID del registro a eliminar.
     * @return El número de filas afectadas.
     */
    fun eliminarMarca(id: Int): Int {
        val db = dbHelper.writableDatabase
        return db.delete(DatabaseHelper.TABLE_MARCA, "${DatabaseHelper.COLUMN_ID} = ?", arrayOf(id.toString()))
    }

    /**
     * Actualiza un registro de asistencia existente.
     *
     * @param marca Objeto `Marca` con los datos actualizados.
     * @return El número de filas afectadas.
     */
    fun actualizarMarca(marca: Marca): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_RUT, marca.rut)
            put(DatabaseHelper.COLUMN_NOMBRE, marca.nombre)
            put(DatabaseHelper.COLUMN_APELLIDO, marca.apellido)
            put(DatabaseHelper.COLUMN_FECHA_HORA, marca.fechaHora)
            put(DatabaseHelper.COLUMN_TIPO, marca.tipo)
        }
        return db.update(DatabaseHelper.TABLE_MARCA, values, "${DatabaseHelper.COLUMN_ID} = ?", arrayOf(marca.id.toString()))
    }
}
