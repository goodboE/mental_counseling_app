package com.example.temp_home_activity.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.temp_home_activity.R
import com.example.temp_home_activity.databinding.FragmentSearchBinding
import com.example.temp_home_activity.model.CounselorModel

class SearchFragment : Fragment(R.layout.fragment_search) {

    private var binding: FragmentSearchBinding? = null
    private lateinit var counselorSearchAdapter: CounselorSearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentSearchBinding = FragmentSearchBinding.bind(view)
        binding = fragmentSearchBinding

        counselorSearchAdapter = CounselorSearchAdapter()
        counselorSearchAdapter.submitList(mutableListOf<CounselorModel>().apply {
            add(CounselorModel("0", "고현진", "잘합니다다다다다다다.", "50000", "1", mutableListOf("가족", "사랑"),"" ))
            add(CounselorModel("1", "신예진", "진짜잘합니다.", "120000", "2", mutableListOf("연애", "심리"),"" ))
            add(CounselorModel("2", "지수아", "개잘합니다.", "220000", "44", mutableListOf("치유", "자연"),"" ))
            add(CounselorModel("3", "최인기", "짱잘합니다.", "200", "55", mutableListOf("학업", "진로"),"" ))
            add(CounselorModel("0", "고현진", "잘합니다다다다다다다.", "50000", "11", mutableListOf("가족", "사랑"),"" ))
            add(CounselorModel("0", "고현진", "잘합니다다다다다다다.", "50000", "123", mutableListOf("가족", "사랑"),"" ))
            add(CounselorModel("0", "고현진", "잘합니다다다다다다다.", "50000", "23", mutableListOf("가족", "사랑"),"" ))
            add(CounselorModel("0", "고현진", "잘합니다다다다다다다.", "50000", "232", mutableListOf("가족", "사랑"),"" ))
            add(CounselorModel("0", "고현진", "잘합니다다다다다다다.", "50000", "233", mutableListOf("가족", "사랑"),"" ))
            add(CounselorModel("0", "고현진", "잘합니다다다다다다다.", "50000", "44", mutableListOf("가족", "사랑"),"" ))
            add(CounselorModel("0", "고현진", "잘합니다다다다다다다.", "50000", "12", mutableListOf("가족", "사랑"),"" ))
        })

        fragmentSearchBinding.searchCounselorRecyclerView.layoutManager = LinearLayoutManager(context)
        fragmentSearchBinding.searchCounselorRecyclerView.adapter = counselorSearchAdapter
    }


}