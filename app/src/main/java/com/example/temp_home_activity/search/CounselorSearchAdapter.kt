package com.example.temp_home_activity.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.temp_home_activity.R
import com.example.temp_home_activity.databinding.ItemCounselorInSearchBinding
import com.example.temp_home_activity.home.CounselorAdapter.Companion.diffUtil
import com.example.temp_home_activity.model.CounselorModel

class CounselorSearchAdapter: ListAdapter<CounselorModel, CounselorSearchAdapter.CounselorSearchViewHolder>(diffUtil) {

    inner class CounselorSearchViewHolder(private val binding: ItemCounselorInSearchBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(counselorModel: CounselorModel) {

            binding.counselorNameInSearchFrag.text = counselorModel.counselorName
            binding.counselorIntroductionInSearchFrag.text = counselorModel.counselorIntroduction
            binding.counselorHeartInSearchFragment.text = counselorModel.counselorHeart

            Glide.with(binding.counselorImageView)
                .load(counselorModel.counselorImageUrl)
                .error(R.drawable.counselorimage)
                .into(binding.counselorImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CounselorSearchViewHolder {
        return CounselorSearchViewHolder(ItemCounselorInSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CounselorSearchViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}