package com.example.controlTotal.database

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class MarcaProvider : ContentProvider() {

    companion object {
        private const val MARCAS = 1
        private const val MARCA_ID = 2
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(MarcaContract.AUTHORITY, MarcaContract.PATH_MARCA, MARCAS)
            addURI(MarcaContract.AUTHORITY, "${MarcaContract.PATH_MARCA}/#", MARCA_ID)
        }
    }

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(): Boolean {
        databaseHelper = DatabaseHelper(context!!)
        return true
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        val db = databaseHelper.readableDatabase
        return when (uriMatcher.match(uri)) {
            MARCAS -> db.query(MarcaContract.MarcaEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder)
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db = databaseHelper.writableDatabase
        val id = db.insert(MarcaContract.MarcaEntry.TABLE_NAME, null, values)
        context?.contentResolver?.notifyChange(uri, null)
        return Uri.withAppendedPath(MarcaContract.MarcaEntry.CONTENT_URI, id.toString())
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val db = databaseHelper.writableDatabase
        return db.delete(MarcaContract.MarcaEntry.TABLE_NAME, selection, selectionArgs)
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        val db = databaseHelper.writableDatabase
        return db.update(MarcaContract.MarcaEntry.TABLE_NAME, values, selection, selectionArgs)
    }

    override fun getType(uri: Uri): String? = null
}