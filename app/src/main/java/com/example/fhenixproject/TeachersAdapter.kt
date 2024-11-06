package com.example.fhenixproject

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fhenixproject.databinding.TeacherViewBinding

class TeachersAdapter(private val context: Context, private val teachers: MutableList<Teachers>) :
    RecyclerView.Adapter<TeachersAdapter.TeachersViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeachersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TeacherViewBinding.inflate(layoutInflater, parent, false)
        val teachersViewHolder = TeachersViewHolder(binding)
        return teachersViewHolder
    }

    override fun onBindViewHolder(
        holder: TeachersViewHolder,
        position: Int
    ) {
        holder.bind(teachers[position])
    }

    override fun getItemCount(): Int {
        return teachers.size
    }

    inner class TeachersViewHolder(val binding: TeacherViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teachers: Teachers) {
            binding.apply {
                image.setImageResource(teachers.imageRes)
                name.text = teachers.name
                subject.text = teachers.subject
            }
        }
    }


}