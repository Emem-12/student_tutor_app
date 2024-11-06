package com.example.fhenixproject

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fhenixproject.databinding.ProvinceRecyclerBinding

class ProvinceAdapter(
    private val provinces: MutableList<Province>,
    private var clickedPositions: MutableSet<Int>
) :
    RecyclerView.Adapter<ProvinceAdapter.ProvinceViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProvinceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProvinceRecyclerBinding.inflate(layoutInflater, parent, false)
        val provinceViewHolder = ProvinceViewHolder(binding)
        return provinceViewHolder
    }

    override fun onBindViewHolder(
        holder: ProvinceViewHolder,
        position: Int
    ) {
        holder.bind(provinces[position], position)
    }

    override fun getItemCount(): Int {
        return provinces.size
    }


    inner class ProvinceViewHolder(val binding: ProvinceRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(province: Province, position: Int) {
            if (clickedPositions.contains(position)) {
                binding.provinceBtn.setBackgroundColor(Color.parseColor("#6336B3"))
            } else {
                binding.provinceBtn.setBackgroundColor(Color.parseColor("#D9D9D9"))
            }

            binding.provinceBtn.setOnClickListener {
                if (clickedPositions.contains(position)) {
                    clickedPositions.remove(position)
                    binding.provinceBtn.setBackgroundColor(Color.parseColor("#6336B3"))
                } else {
                    clickedPositions.add(position)
                    binding.provinceBtn.setBackgroundColor(Color.parseColor("#D9D9D9"))
                }
            }

            binding.apply {
                provinceBtn.text = province.name
            }
        }


    }

}