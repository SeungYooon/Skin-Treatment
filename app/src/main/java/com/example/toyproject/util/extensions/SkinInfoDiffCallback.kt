package com.example.toyproject.util.extensions

import androidx.recyclerview.widget.DiffUtil
import com.example.toyproject.data.db.entities.SkinInfo

class SkinInfoDiffCallback : DiffUtil.ItemCallback<SkinInfo>() {
    override fun areItemsTheSame(oldItem: SkinInfo, newItem: SkinInfo): Boolean =
        oldItem.skinKinds == newItem.skinKinds

    override fun areContentsTheSame(oldItem: SkinInfo, newItem: SkinInfo): Boolean =
        oldItem == newItem
}