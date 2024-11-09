package com.example.fhenixproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.example.fhenixproject.databinding.FragmentGradeSelectionBinding

class GradeSelectionFragment : Fragment() {
    private lateinit var binding: FragmentGradeSelectionBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGradeSelectionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.grade1to5Toggle.setOnClickListener {
            loadPopmenu1()
        }
        binding.grade6to9Toggle.setOnClickListener {
            loadPopmenu2()
        }
        binding.grade10to11Toggle.setOnClickListener {
            loadPopmenu3()
        }
        binding.grade12to13Toggle.setOnClickListener {
            loadPopmenu4()
        }
        binding.nextBtn.setOnClickListener {
            loadProvinceFragment()
        }
        binding.skipTxt.setOnClickListener {
            loadProvinceFragment()
        }
    }

    fun loadProvinceFragment() {
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_view, ProvinceFragment())
        fragmentTransaction.addToBackStack(null).commit()
    }

    fun loadPopmenu1() {
        val popupMenu = PopupMenu(requireContext(), binding.grade1to5Toggle)
        popupMenu.menuInflater.inflate(R.menu.dropdown_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            Toast.makeText(requireContext(), "Selected:${item.title}", Toast.LENGTH_SHORT).show()
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()
    }

    fun loadPopmenu2() {
        val popupMenu = PopupMenu(requireContext(), binding.grade6to9Toggle)
        popupMenu.menuInflater.inflate(R.menu.dropdown_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            Toast.makeText(requireContext(), "Selected:${item.title}", Toast.LENGTH_SHORT).show()
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()
    }


    fun loadPopmenu3() {
        val popupMenu = PopupMenu(requireContext(), binding.grade10to11Toggle)
        popupMenu.menuInflater.inflate(R.menu.dropdown_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            Toast.makeText(requireContext(), "Selected:${item.title}", Toast.LENGTH_SHORT).show()
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()
    }


    fun loadPopmenu4() {
        val popupMenu = PopupMenu(requireContext(), binding.grade12to13Toggle)
        popupMenu.menuInflater.inflate(R.menu.dropdown_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            Toast.makeText(requireContext(), "Selected:${item.title}", Toast.LENGTH_SHORT).show()
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()
    }



}