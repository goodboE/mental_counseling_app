package com.testor.whynot.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.actionCodeSettings
import com.testor.whynot.MainActivity
import com.testor.whynot.R
import com.testor.whynot.databinding.FragmentHomeBinding
import com.testor.whynot.home.login.LoginFragment
import com.testor.whynot.model.CounselorModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null
    private lateinit var counselorAdapter: CounselorAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentHomeBinding = FragmentHomeBinding.bind(view)
        binding = fragmentHomeBinding

        val mActivity = activity as MainActivity
        fragmentHomeBinding.homeLoginButton.setOnClickListener {
            mActivity.changeFragment(LoginFragment())
        }


        counselorAdapter = CounselorAdapter()

        // todo input data

        fragmentHomeBinding.homeCounselorRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        fragmentHomeBinding.homeCounselorRecyclerView.scrollToPosition(counselorAdapter.itemCount - 1)
        fragmentHomeBinding.homeCounselorRecyclerView.adapter = counselorAdapter

    }



    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }



}