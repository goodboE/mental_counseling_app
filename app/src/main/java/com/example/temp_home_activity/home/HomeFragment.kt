package com.example.temp_home_activity.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.temp_home_activity.R
import com.example.temp_home_activity.databinding.FragmentHomeBinding
import com.example.temp_home_activity.model.CounselorModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null
    private lateinit var counselorAdapter: CounselorAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentHomeBinding = FragmentHomeBinding.bind(view)
        binding = fragmentHomeBinding

        counselorAdapter = CounselorAdapter()
        counselorAdapter.submitList(mutableListOf<CounselorModel>().apply {
            add(CounselorModel("0", "고현진", "잘합니다다다다다다다.", "50000", "♥♥♥", mutableListOf("가족", "사랑"),"" ))
            add(CounselorModel("1", "신예진", "진짜잘합니다.", "120000", "♥♥♥♥♥", mutableListOf("연애", "심리"),"" ))
            add(CounselorModel("2", "지수아", "개잘합니다.", "220000", "♥♥", mutableListOf("치유", "자연"),"" ))
            add(CounselorModel("3", "최인기", "짱잘합니다.", "200", "♥♥♥♥", mutableListOf("학업", "진로"),"" ))
        })

        fragmentHomeBinding.homeCounselorRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        fragmentHomeBinding.homeCounselorRecyclerView.scrollToPosition(counselorAdapter.itemCount - 1)
        fragmentHomeBinding.homeCounselorRecyclerView.adapter = counselorAdapter

    }

}