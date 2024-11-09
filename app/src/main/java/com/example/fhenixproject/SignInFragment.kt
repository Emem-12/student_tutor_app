package com.example.fhenixproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fhenixproject.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var emailRegex: Regex

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater)
        auth = Firebase.auth
        emailRegex = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")


        binding.signinBtn.setOnClickListener {
            loginUser(
                binding.email.text.toString(),
                binding.password.text.toString()
            )
        }
        binding.signUpTxt.setOnClickListener {
            loadSignUpFragment()
        }
        return binding.root
    }

    private fun loginUser(email: String, password: String) {
        if (email.isEmpty()) {
            binding.email.error = "Email is required"
            return
        } else if (!emailRegex.matches(email)) {
            binding.email.error = "Invalid email format"
            return
        }

        if (password.isEmpty()) {
            binding.password.error = "Password is required"
            return
        } else if (password.length < 8) {
            binding.password.error = "Password must be at least 8 characters"
            return
        }

        try {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val user =auth.currentUser
                    println("Login successful: ${user?.email}")
                    loadGradeSelectionFragment()
                } else {
                    Toast.makeText(context, "email or password mismatch", Toast.LENGTH_SHORT).show()
                    Log.e(tag, "loginUser failed: ${it.exception?.message}")
                }
            }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            Log.e(tag, "loginUser exception: $e")
        }

    }

    fun loadGradeSelectionFragment() {
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_view, GradeSelectionFragment())
        fragmentTransaction.addToBackStack(null).commit()
    }

    fun loadSignUpFragment() {
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_view, SignUpFragment())
        fragmentTransaction.addToBackStack(null).commit()

    }

}