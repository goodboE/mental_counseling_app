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
import com.google.firebase.ktx.Firebase
import com.testor.whynot.MainActivity
import com.testor.whynot.R
import com.testor.whynot.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var binding: FragmentLoginBinding? = null
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentLoginBinding = FragmentLoginBinding.bind(view)
        binding = fragmentLoginBinding

        auth = Firebase.auth

        initSignInButton(fragmentLoginBinding)
        initSignUpButton(fragmentLoginBinding)
        initEmailAndPasswordEditText(fragmentLoginBinding)


    }



    private fun initSignInButton(fragmentLoginBinding: FragmentLoginBinding) {
        fragmentLoginBinding.signInButton.setOnClickListener {
            val email = fragmentLoginBinding.emailEditText.text.toString()
            val password = fragmentLoginBinding.passwordEditText.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(requireActivity(), "회원가입 성공, 로그인 하세요", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireActivity(), "이미 가입한 이메일 or 회원가입 실패", Toast.LENGTH_SHORT).show()
                        Log.d("signinerror", task.exception?.message.toString())
                    }
                }

        }
    }

    private fun initSignUpButton(fragmentLoginBinding: FragmentLoginBinding) {
        fragmentLoginBinding.signUpButton.setOnClickListener {
            val email = fragmentLoginBinding.emailEditText.text.toString()
            val password = fragmentLoginBinding.passwordEditText.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        activity?.supportFragmentManager
                            ?.beginTransaction()
                            ?.remove(this)
                            ?.commit()
                    } else {
                        Toast.makeText(requireActivity(), "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }

    private fun initEmailAndPasswordEditText(fragmentLoginBinding: FragmentLoginBinding) {
        val emailEditText = fragmentLoginBinding.emailEditText
        val passwordEditText = fragmentLoginBinding.passwordEditText
        val signInButton = fragmentLoginBinding.signInButton
        val signUpButton = fragmentLoginBinding.signUpButton

        emailEditText.addTextChangedListener {
            val enable = emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()
            signInButton.isEnabled = enable
            signUpButton.isEnabled = enable
        }

        passwordEditText.addTextChangedListener {
            val enable = emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()
            signInButton.isEnabled = enable
            signUpButton.isEnabled = enable
        }


    }




}