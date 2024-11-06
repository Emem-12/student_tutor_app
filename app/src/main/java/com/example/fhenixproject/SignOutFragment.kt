package com.example.fhenixproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fhenixproject.databinding.FragmentSignOutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignOutFragment : Fragment() {
    private lateinit var binding: FragmentSignOutBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var emailRegex: Regex


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignOutBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        emailRegex = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")

        binding.signInTxt.setOnClickListener {
            loadSignInFragment()
        }
        binding.signupBtn.setOnClickListener {
            signUp(
                binding.name.text.toString(),
                binding.email.text.toString(),
                binding.password.text.toString()
            )
            loadSignInFragment()

        }

        return binding.root
    }

    private fun loadUserData() {
        val currentUserID = auth.currentUser!!.uid

        firestore.collection("users").document(currentUserID).get().addOnCompleteListener {
            if (it.isSuccessful) {
                if (it.result.exists()) {
                    val user = it.result.toObject(User::class.java)
                    binding.apply {
                        name.setText(user?.name)
                        email.setText(user?.email)
                        password.setText(user?.password)
                    }
                } else {
                    Toast.makeText(context, "User data not found.", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun signUp(email: String, password: String, name: String) {
        if (email.isNotEmpty() && name.isNotEmpty() && password.isNotEmpty()) {
            if (!emailRegex.matches(email)) {
                binding.emailTxt.error = "invalid email"
                return
            }

            if (password.length < 8) {
                binding.passwordTxt.error = "password too short"
                return
            }

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Sign up successful!", Toast.LENGTH_LONG).show()
                    auth.currentUser?.sendEmailVerification()
                    saveUserInfo(name, email, password)
                } else {
                    Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(context, "All field are required", Toast.LENGTH_LONG).show()
        }

    }

    private fun saveUserInfo(name: String, email: String, password: String) {
        if (name.isBlank() || name.length < 2) {
            binding.name.error = "Your name is too short"
            return
        }
        if (email.isBlank() || email.length < 2) {
            binding.email.error = "Your name is too short"
            return
        }
        if (password.isBlank() || password.length < 2) {
            binding.password.error = "Your name is too short"
            return
        }

        val user = User(auth.currentUser!!.uid, name.trim(), email.trim(), password.trim())
        try {
            firestore.collection("users").document(auth.currentUser!!.uid).set(user)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            context,
                            "Data has been set successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        loadSignInFragment()
                    } else {
                        Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            Log.e(tag, "loginUser exception: $e")
        }

    }


    fun loadSignInFragment() {
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_view, SignInFragment())
        fragmentTransaction.addToBackStack(null).commit()
    }


}

