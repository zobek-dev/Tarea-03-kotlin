package com.example.controlTotal.ui

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.controlTotal.R
import com.example.controlTotal.database.BaseDeDatosPersona
import com.example.controlTotal.model.Marca
import com.example.controlTotal.utils.DateUtils

class RegistroActivity : AppCompatActivity() {

    private lateinit var etRut: EditText
    private lateinit var etNombre: EditText
    private lateinit var etApellido: EditText
    private lateinit var rgTipoMarca: RadioGroup
    private lateinit var btnGuardar: Button
    private lateinit var db: BaseDeDatosPersona

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        // Inicializar vistas
        etRut = findViewById(R.id.etRut)
        etNombre = findViewById(R.id.etNombre)
        etApellido = findViewById(R.id.etApellido)
        rgTipoMarca = findViewById(R.id.rgTipoMarca)
        btnGuardar = findViewById(R.id.btnGuardar)

        // Inicializar base de datos
        db = BaseDeDatosPersona(this)

        // Configurar el botón para guardar
        btnGuardar.setOnClickListener {
            guardarAsistencia()
        }
    }

    private fun guardarAsistencia() {
        val rut = etRut.text.toString().trim()
        val nombre = etNombre.text.toString().trim()
        val apellido = etApellido.text.toString().trim()

        // Validar campos
        if (rut.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show()
            return
        }

        val tipoMarca = when (rgTipoMarca.checkedRadioButtonId) {
            R.id.rbEntrada -> "Entrada"
            R.id.rbSalida -> "Salida"
            else -> {
                Toast.makeText(this, "Por favor, seleccione el tipo de marca.", Toast.LENGTH_SHORT).show()
                return
            }
        }

        val fechaHora = DateUtils.getCurrentDateTime()

        // Crear objeto Marca y guardar en la base de datos
        val marca = Marca(
            rut = rut,
            nombre = nombre,
            apellido = apellido,
            fechaHora = fechaHora,
            tipo = tipoMarca
        )
        val resultado = db.insertarMarca(marca)

        if (resultado > 0) {
            Toast.makeText(this, "Asistencia registrada correctamente.", Toast.LENGTH_SHORT).show()
            finish() // Regresar a la actividad anterior
        } else {
            Toast.makeText(this, "Error al guardar la asistencia.", Toast.LENGTH_SHORT).show()
        }
    }
}
