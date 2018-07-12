package com.example.ujap.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ujap.Model.Materia
import com.example.ujap.R
import kotlinx.android.synthetic.main.materia_list_item.view.*
import org.w3c.dom.Text

class MateriasAdapter(val context: Context, val materias: List<Materia>): RecyclerView.Adapter<MateriasAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.materia_list_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return materias.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindMateria(materias[position], context)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val materiaName = itemView.findViewById<TextView>(R.id.materiaName)
        val materiaStatus = itemView.findViewById<TextView>(R.id.materiaStatus)

        fun bindMateria(materia: Materia, context: Context) {
            materiaName.text = materia.nombre
            materiaStatus.text = materia.estado
        }
    }
}