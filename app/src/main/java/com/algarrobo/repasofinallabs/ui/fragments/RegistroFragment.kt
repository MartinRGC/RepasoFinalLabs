package com.algarrobo.repasofinallabs.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.algarrobo.repasofinallabs.Models.CursoModel
import com.algarrobo.repasofinallabs.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID


class RegistroFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_registro, container, false)

        val txtcourse: EditText = view.findViewById(R.id.txtcourse)
        val txtscore: EditText = view.findViewById(R.id.txtscore)
        val btnsave: Button = view.findViewById(R.id.btnsave)

        val db = FirebaseFirestore.getInstance()

        btnsave.setOnClickListener {
            // obtener lo que escribio el usuario
            var curso = txtcourse.text.toString()
            var nota = txtscore.text.toString()
            // generar un objeto del tipo curso

            val CursoModel = CursoModel(curso, nota) //

            val id: UUID = UUID.randomUUID() // GENERAMOS UN ID RANDOM PARA CADA NUEVA COLLECCION QUE SE INGRESE
            db.collection("courses") // a que collección la vamos a mandar
                .document(id.toString()) // a que documento en firebase
                .set(CursoModel) // el objeto que se va a registrar
                .addOnSuccessListener {
                    enviarmensaje(view,"Registro exitoso")

                }.addOnFailureListener{
                    enviarmensaje(view,"Ocurrio un problema en el registro")

                }
        }
        return view
    }

}

private fun enviarmensaje(vista:View,mensaje:String){
    Snackbar.make(vista,mensaje,Snackbar.LENGTH_LONG).show()
}
