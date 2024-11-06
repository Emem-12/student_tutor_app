package com.example.fhenixproject

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fhenixproject.databinding.InstitutionViewBinding

class InstitutionAdapter(
    private val context: Context,
    private val institutions: MutableList<Institution>
) :
    RecyclerView.Adapter<InstitutionAdapter.InstitutionViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InstitutionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = InstitutionViewBinding.inflate(layoutInflater, parent, false)
        val institutionViewHolder = InstitutionViewHolder(binding)
        return institutionViewHolder
    }

    override fun onBindViewHolder(
        holder: InstitutionViewHolder,
        position: Int
    ) {
        holder.bind(institutions[position])
    }

    override fun getItemCount(): Int {
        return institutions.size
    }

    inner class InstitutionViewHolder(val binding: InstitutionViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(institution: Institution) {
            binding.apply {
                image.setImageResource(institution.imageRes)
                name.text = institution.name
                subject.text = institution.subject
                description.text = institution.description
                //binding.card.setCardBackgroundColor(context.resources.getColor(institution.color,null))
            }
        }
    }
}