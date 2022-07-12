package com.testor.whynot.home.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.testor.whynot.MainActivity
import com.testor.whynot.R
import com.testor.whynot.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var binding: FragmentLoginBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var userDB: DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentLoginBinding = FragmentLoginBinding.bind(view)
        binding = fragmentLoginBinding

        auth = Firebase.auth

        initSignInButton(fragmentLoginBinding)
        initSignUpButton(fragmentLoginBinding)
        initEmailAndPasswordEditText(fragmentLoginBinding)


    }


    private fun initSignUpButton(fragmentLoginBinding: FragmentLoginBinding) {
        fragmentLoginBinding.signUpButton.setOnClickListener {
            val email = fragmentLoginBinding.emailEditText.text.toString()
            val password = fragmentLoginBinding.passwordEditText.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        handleSuccessLogin(fragmentLoginBinding)
                        Toast.makeText(requireActivity(), "회원가입 성공, 로그인 하세요", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            requireActivity(),
                            "이미 가입한 이메일 or 회원가입 실패",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("signUperror", task.exception?.message.toString())
                    }
                }

        }
    }

    private fun initSignInButton(fragmentLoginBinding: FragmentLoginBinding) {
        fragmentLoginBinding.signInButton.setOnClickListener {
            val email = fragmentLoginBinding.emailEditText.text.toString()
            val password = fragmentLoginBinding.passwordEditText.text.toString()

            if (auth.currentUser == null) {

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(requireActivity(), "로그인 성공", Toast.LENGTH_SHORT).show()
                            successSignIn()
                        } else {
                            Toast.makeText(requireActivity(), "로그인 실패", Toast.LENGTH_SHORT).show()
                            Log.d("signInError", task.exception?.message.toString())
                        }
                    }
            } else {
                auth.signOut()
                fragmentLoginBinding.emailEditText.text.clear()
                fragmentLoginBinding.emailEditText.isEnabled = true
                fragmentLoginBinding.nameEditText.text.clear()
                fragmentLoginBinding.nameEditText.isEnabled = true
                fragmentLoginBinding.passwordEditText.text.clear()
                fragmentLoginBinding.passwordEditText.isEnabled = true

                fragmentLoginBinding.signInButton.text = "로그인"
                fragmentLoginBinding.signInButton.isEnabled = true
                fragmentLoginBinding.signUpButton.isEnabled = false
            }
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Toast.makeText(requireActivity(), "로그인 성공", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(requireActivity(), "로그인 실패", Toast.LENGTH_SHORT).show()
                        Log.d("signInError", task.exception?.message.toString())
                    }
                }

        }
    }

    private fun initEmailAndPasswordEditText(fragmentLoginBinding: FragmentLoginBinding) {
        val emailEditText = fragmentLoginBinding.emailEditText
        val passwordEditText = fragmentLoginBinding.passwordEditText
        val nameEditText = fragmentLoginBinding.nameEditText
        val signInButton = fragmentLoginBinding.signInButton
        val signUpButton = fragmentLoginBinding.signUpButton

        emailEditText.addTextChangedListener {
            var enable =
                emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty() && nameEditText.text.isNotEmpty()
            signInButton.isEnabled = enable
            signUpButton.isEnabled = enable
        }

        passwordEditText.addTextChangedListener {
            var enable =
                emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty() && nameEditText.text.isNotEmpty()
            signInButton.isEnabled = enable
            signUpButton.isEnabled = enable
        }

        nameEditText.addTextChangedListener {
            var enable =
                emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty() && nameEditText.text.isNotEmpty()
            signInButton.isEnabled = enable
            signUpButton.isEnabled = enable
        }


    }

    private fun handleSuccessLogin(fragmentLoginBinding: FragmentLoginBinding) {

        val name = fragmentLoginBinding.nameEditText.text.toString()
        if (auth.currentUser == null) {
            // Toast.makeText(requireActivity(), "로그인 실패", Toast.LENGTH_SHORT).show()
            return
        }

        val userId = auth.currentUser?.uid.orEmpty()
        val currentUserDB = Firebase.database.reference.child("Users").child(userId)
        val user = mutableMapOf<String, Any>()
        user["userId"] = userId
        user["name"] = name
        currentUserDB.updateChildren(user)

    }

    override fun onStart() {
        super.onStart()

        if (auth.currentUser == null) {
            binding?.let { binding ->
                binding.signInButton.text = "로그인"

                binding.emailEditText.text.clear()
                binding.nameEditText.text.clear()
                binding.passwordEditText.text.clear()

                binding.emailEditText.isEnabled = true
                binding.nameEditText.isEnabled = true
                binding.passwordEditText.isEnabled = true

                binding.signInButton.isEnabled = false
                binding.signUpButton.isEnabled = false
            }
        } else {
            binding?.let { binding ->
                binding.signInButton.text = "로그아웃"

                binding.emailEditText.setText(auth.currentUser!!.email)
                binding.nameEditText.setText("name")
                binding.passwordEditText.setText("******")

                binding.signInButton.isEnabled = true
                binding.signUpButton.isEnabled = false

                binding.emailEditText.isEnabled = true
                binding.nameEditText.isEnabled = true
                binding.passwordEditText.isEnabled = true
            }
        }
    }

    private fun successSignIn() {
        if (auth.currentUser == null) {
            Toast.makeText(requireActivity(), "로그인 실패, 다시 시도 바람", Toast.LENGTH_SHORT).show()
            return
        }
        binding?.emailEditText?.isEnabled = false
        binding?.nameEditText?.isEnabled = false
        binding?.passwordEditText?.isEnabled = false
        binding?.signInButton?.text = "로그아웃"
        binding?.signUpButton?.isEnabled = false
    }


//    private fun saveUserName(name: String) {
//        val userId = auth.currentUser?.uid.orEmpty()
//        val currentUserDB = Firebase.database.reference.child("Users").child(userId)
//        val user = mutableMapOf<String, Any>()
//        user["userId"] = userId
//        user["name"] = name
//        currentUserDB.updateChildren(user)
//    }



}
