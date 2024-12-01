package com.example.controlTotal.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.controlTotal.R
import com.example.controlTotal.database.BaseDeDatosPersona
import com.example.controlTotal.model.Marca
import com.example.controlTotal.utils.DateUtils

class MainActivity : AppCompatActivity() {

    private lateinit var db: BaseDeDatosPersona
    private lateinit var listView: ListView
    private lateinit var btnAgregar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar la base de datos
        db = BaseDeDatosPersona(this)

        // Inicializar las vistas
        listView = findViewById(R.id.listView)
        btnAgregar = findViewById(R.id.btnAgregar)

        // Configurar el botón para agregar un nuevo registro
        btnAgregar.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        // Cargar la lista inicial
        cargarLista()
    }

    override fun onResume() {
        super.onResume()
        // Recargar la lista al volver a esta actividad
        cargarLista()
    }

    private fun cargarLista() {
        val marcas: List<Marca> = db.obtenerTodasLasMarcas()

        if (marcas.isEmpty()) {
            Toast.makeText(this, "No hay registros de asistencia.", Toast.LENGTH_SHORT).show()
        } else {
            val adaptador = AdaptadorMarca(this, marcas)
            listView.adapter = adaptador
        }
    }
}
