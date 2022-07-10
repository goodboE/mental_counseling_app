package com.testor.whynot.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.testor.whynot.R
import com.testor.whynot.databinding.FragmentSearchBinding
import com.testor.whynot.model.CounselorModel

class SearchFragment : Fragment(R.layout.fragment_search) {

    private var binding: FragmentSearchBinding? = null
    private lateinit var counselorSearchAdapter: CounselorSearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentSearchBinding = FragmentSearchBinding.bind(view)
        binding = fragmentSearchBinding

        counselorSearchAdapter = CounselorSearchAdapter()

        // todo input data

        fragmentSearchBinding.searchCounselorRecyclerView.layoutManager = LinearLayoutManager(context)
        fragmentSearchBinding.searchCounselorRecyclerView.adapter = counselorSearchAdapter
    }


}