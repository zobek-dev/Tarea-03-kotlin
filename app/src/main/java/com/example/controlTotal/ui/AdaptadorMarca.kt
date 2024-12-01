package com.example.controlTotal.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.controlTotal.R
import com.example.controlTotal.model.Marca

class AdaptadorMarca(private val context: Context, private val marcas: List<Marca>) : BaseAdapter() {

    override fun getCount(): Int = marcas.size

    override fun getItem(position: Int): Any = marcas[position]

    override fun getItemId(position: Int): Long = marcas[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_marca, parent, false)

        // Obtener referencias de las vistas
        val tvRut: TextView = view.findViewById(R.id.tvRut)
        val tvFechaHora: TextView = view.findViewById(R.id.tvFechaHora)
        val tvTipo: TextView = view.findViewById(R.id.tvTipo)

        // Obtener la marca actual
        val marca = getItem(position) as Marca

        // Asignar valores a las vistas
        tvRut.text = "RUT: ${marca.rut}"
        tvFechaHora.text = "Fecha/Hora: ${marca.fechaHora}"
        tvTipo.text = "Tipo: ${marca.tipo}"

        return view
    }
}
