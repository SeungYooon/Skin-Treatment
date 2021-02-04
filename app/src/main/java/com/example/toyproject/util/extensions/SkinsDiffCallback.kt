package com.example.toyproject.util.extensions

import androidx.recyclerview.widget.DiffUtil
import com.example.toyproject.data.db.entities.Skins

class SkinsDiffCallback : DiffUtil.ItemCallback<Skins>() {
    override fun areItemsTheSame(oldItem: Skins, newItem: Skins): Boolean =
        oldItem.skinKinds == newItem.skinKinds

    override fun areContentsTheSame(oldItem: Skins, newItem: Skins): Boolean =
        oldItem == newItem

}