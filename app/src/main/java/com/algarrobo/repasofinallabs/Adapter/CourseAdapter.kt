package com.algarrobo.repasofinallabs.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.algarrobo.repasofinallabs.Models.CursoModel
import com.algarrobo.repasofinallabs.R

class CourseAdapter(private var lstCourse: List<CursoModel>):RecyclerView.Adapter<CourseAdapter.ViewHolder>(){
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val tvDescription: TextView = itemView.findViewById(R.id.tvcurso)
        val tvScore: TextView = itemView.findViewById(R.id.tvnota)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val  layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_course,parent,false))
    }

    override fun getItemCount(): Int {
        return lstCourse.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val itemcourse = lstCourse[position]
            holder.tvDescription.text = itemcourse.description
            holder.tvScore.text = itemcourse.score

    }


}