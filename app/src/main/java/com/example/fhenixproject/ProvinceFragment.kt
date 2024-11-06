package com.example.fhenixproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fhenixproject.databinding.FragmentProvinceBinding

class ProvinceFragment : Fragment() {
    private lateinit var binding: FragmentProvinceBinding
    private lateinit var provinceAdapter: ProvinceAdapter
    private lateinit var listOfProvince: MutableList<Province>
    private val clickedPositions = mutableSetOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProvinceBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextBtn.setOnClickListener {
            loadHomeFragment()
        }
        binding.skipTxt.setOnClickListener {
            loadHomeFragment()
        }
        val province1 = Province("Central")
        val province2 = Province("Eastern")
        val province3 = Province("North Central")
        val province4 = Province("Northern")
        val province5 = Province("North Western")
        val province6 = Province("Sabaragamuwa")
        val province7 = Province("Southern")
        val province8 = Province("Uva")
        val province9 = Province("Western")



        listOfProvince = mutableListOf(
            province1, province2, province3,
            province4, province5, province6,
            province7, province8,
            province9
        )

        provinceAdapter = ProvinceAdapter(listOfProvince, clickedPositions)

        binding.provinceRecycler.adapter = provinceAdapter
        binding.provinceRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    fun loadHomeFragment() {
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_view, HomeFragment())
        fragmentTransaction.addToBackStack(null).commit()
    }


}