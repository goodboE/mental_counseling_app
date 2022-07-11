package com.testor.whynot.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.testor.whynot.R
import com.testor.whynot.databinding.FragmentHomeBinding


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null
    private lateinit var counselorAdapter: CounselorAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentHomeBinding = FragmentHomeBinding.bind(view)
        binding = fragmentHomeBinding

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



    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }


}