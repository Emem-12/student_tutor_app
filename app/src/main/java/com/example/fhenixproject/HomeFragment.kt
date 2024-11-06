package com.example.fhenixproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fhenixproject.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var institutionAdapter: InstitutionAdapter
    private lateinit var teachersAdapter: TeachersAdapter
    private lateinit var listOfTeachers: MutableList<Teachers>
    private lateinit var listOfInstitution: MutableList<Institution>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.search.setOnClickListener {
        }


        val institution1 = Institution(
            R.drawable.institution_icon1,
            "Concordia University",
            "Computer Science and Technology",
            "hiuvbv hdsijc buhudhiuh uhdfajkjjoihf hhiujfjfdnhk hfjhui"
        )
        val institution2 = Institution(
            R.drawable.tutor2_2,
            "AKSU",
            "Arts",
            "hiuvbv hdsijc buhudhiuh uhdfajkjjoihf hhiujfjfdnhk hfjhui"
        )
        val institution3 = Institution(
            R.drawable.institute3,
            "Idahosa University",
            "Engineering",
            "hiuvbv hdsijc buhudhiuh uhdfajkjjoihf hhiujfjfdnhk hfjhui"
        )
        listOfInstitution = mutableListOf(institution3, institution2, institution1)
        institutionAdapter = InstitutionAdapter(requireContext(), listOfInstitution)
        binding.instiRecycler.adapter = institutionAdapter
        binding.instiRecycler.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )

        val teacher1 = Teachers(R.drawable.image, "Andrew Scott", "Biology")
        val teacher2 = Teachers(R.drawable.image, "Maria Henry", "Mathematics")
        val teacher3 = Teachers(R.drawable.image, "Mac Zane", "Chemistry")
        val teacher4 = Teachers(R.drawable.image, "Effiong Helen", "Ibibio")
        val teacher5 = Teachers(R.drawable.image, "Osazu Ife", "Physics")

        listOfTeachers = mutableListOf(teacher5, teacher4, teacher1, teacher2, teacher3)
        teachersAdapter = TeachersAdapter(requireContext(), listOfTeachers)
        binding.popularTeachersRecycler.adapter = teachersAdapter
        binding.popularTeachersRecycler.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
    }
}