package com.example.temp_home_activity.home

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.temp_home_activity.CounselorModel
import com.example.temp_home_activity.databinding.ItemCounselorBinding

class CounselorAdapter: ListAdapter<CounselorModel, CounselorAdapter.CounselorViewHolder>(diffUtil) {

    inner class CounselorViewHolder(private val binding: ItemCounselorBinding): RecyclerView.ViewHolder(binding.root) {

    }
}