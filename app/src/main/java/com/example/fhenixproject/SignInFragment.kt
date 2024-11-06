package com.example.fhenixproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fhenixproject.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
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
                binding.emailTxt.text.toString(),
                binding.passwordTxt.text.toString()
            )
            loadProvinceFragment()
        }
        binding.signUpTxt.setOnClickListener {
            loadSignUpFragment()
        }
        return binding.root
    }

    private fun loginUser(email: String, password: String) {
        if (password.isBlank() || password.length < 8) {
            binding.passwordTxt.error = "password is too short"
            return
        }
        if (email.isBlank() || !emailRegex.matches(email)) {
            binding.passwordTxt.error = "input a valid email"
            return
        }
        try {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    loadProvinceFragment()
                } else {
                    Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
                    Log.e(tag, "loginUser failed: ${it.exception?.message}")
                }
            }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            Log.e(tag, "loginUser exception: $e")
        }

    }

    fun loadProvinceFragment() {
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_view, ProvinceFragment())
        fragmentTransaction.addToBackStack(null).commit()
    }

    fun loadSignUpFragment() {
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_view, SignOutFragment())
        fragmentTransaction.addToBackStack(null).commit()

    }

}