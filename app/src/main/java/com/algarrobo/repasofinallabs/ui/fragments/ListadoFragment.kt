package com.algarrobo.repasofinallabs.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.algarrobo.repasofinallabs.Adapter.CourseAdapter
import com.algarrobo.repasofinallabs.Models.CursoModel
import com.algarrobo.repasofinallabs.R
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore


class ListadoFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_listado, container, false)

        val db =  FirebaseFirestore.getInstance()
        val lstcourses: ArrayList<CursoModel> = ArrayList()
        val rvCourse: RecyclerView= view.findViewById(R.id.rvcCourse)

        db.collection("courses")
            .addSnapshotListener{snapshots, e->
                if(e != null){
                    Log.w("Firestore warning", "error",e)
                    return@addSnapshotListener
                }
                for (dc in snapshots!!.documentChanges){
                    when(dc.type){
                        DocumentChange.Type.ADDED -> {
                            lstcourses.add(
                                CursoModel(dc.document.data["description"].toString(),
                                dc.document.data["score"].toString(), dc.document.data["imageUrl"].toString()))
                                rvCourse.adapter = CourseAdapter(lstcourses)
                        }
                        DocumentChange.Type.MODIFIED ->{
                            lstcourses.add(
                                CursoModel(dc.document.data["description"].toString(),
                                    dc.document.data["score"].toString(), dc.document.data["imageUrl"].toString()))
                            rvCourse.adapter = CourseAdapter(lstcourses)

                        }
                        DocumentChange.Type.REMOVED ->{
                            Log.w("Firebase Warning", "REMOVED")
                        }
                    }
                }
                rvCourse.layoutManager = LinearLayoutManager(requireContext())
            }


        // Inflate the layout for this fragment
        return view
    }


}