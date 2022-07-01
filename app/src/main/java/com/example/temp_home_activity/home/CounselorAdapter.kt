package com.example.temp_home_activity.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.temp_home_activity.R
import com.example.temp_home_activity.model.CounselorModel
import com.example.temp_home_activity.databinding.ItemCounselorBinding

class CounselorAdapter: ListAdapter<CounselorModel, CounselorAdapter.CounselorViewHolder>(diffUtil) {

    inner class CounselorViewHolder(private val binding: ItemCounselorBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(counselorModel: CounselorModel) {

            binding.cardViewCounselorName.text = counselorModel.counselorName
            binding.cardViewCounselorHeart.text = counselorModel.counselorHeart
            binding.cardViewCounselorIntroduction.text = counselorModel.counselorIntroduction

            Glide.with(binding.counselorImageView)
                .load(counselorModel.counselorImageUrl)
                .error(R.drawable.counselorimage)
                .into(binding.counselorImageView)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CounselorViewHolder {
        return CounselorViewHolder(ItemCounselorBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CounselorViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<CounselorModel>() {
            override fun areItemsTheSame(oldItem: CounselorModel, newItem: CounselorModel): Boolean {
                return oldItem.counselorId == newItem.counselorId
            }

            override fun areContentsTheSame(oldItem: CounselorModel, newItem: CounselorModel): Boolean {
                return oldItem == newItem
            }
        }
    }


}