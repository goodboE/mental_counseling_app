package com.testor.whynot.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.testor.whynot.R
import com.testor.whynot.databinding.FragmentHomeBinding


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var counselorAdapter: CounselorAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentHomeBinding = FragmentHomeBinding.bind(view)
        binding = fragmentHomeBinding
        auth = Firebase.auth

        fragmentHomeBinding.homeLoginButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_loginFragment)
        }


        counselorAdapter = CounselorAdapter()

        // todo input data

        fragmentHomeBinding.homeCounselorRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        fragmentHomeBinding.homeCounselorRecyclerView.scrollToPosition(counselorAdapter.itemCount - 1)
        fragmentHomeBinding.homeCounselorRecyclerView.adapter = counselorAdapter

        // todo implement backstack ..

    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            binding?.homeLoginButton?.setText(auth.currentUser!!.email + "\n 님 환영합니다!")
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }


}