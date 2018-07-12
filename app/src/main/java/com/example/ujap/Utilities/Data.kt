package com.example.ujap.Utilities

import com.example.ujap.Model.Materia
import com.example.ujap.Model.News

object Data {
    val materias1 = listOf<Materia>(
            Materia("1", "Metodos cuantitativos", "Aprobada"),
            Materia("2", "Arquitectura del Computador", "Aprobada"),
            Materia("3", "Ingenieria del Software", "Reprobada")
    )

    val materias2 = listOf<Materia>(
            Materia("1", "Programacion matematica", "Reprobada"),
            Materia("2", "Estructuras discretas", "Aprobada")
    )

    val news = listOf<News>(
            News("Carnet Provisional", "A partir del 16/05/2018 el uso del carnet sera obligatorio para ingresar en las instalaciones"),
            News("Pruebas de suficiencia", "La presentacion de pruebas sera del 13/08/2018 al 08/09/2018"),
            News("Vestimenta Ujapista", "No se permite el acceso a las instalaciones utilizando gorras, franelillas, shorts, bermudas ni mini faldas")
    )
}