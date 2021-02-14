package com.example.toyproject.ui.myskin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.toyproject.data.db.entities.SkinInfo
import com.example.toyproject.databinding.ItemMySkinBinding
import com.example.toyproject.util.extensions.SkinInfoDiffCallback
import com.example.toyproject.util.extensions.bindImage
import javax.inject.Inject

class MySkinAdapter @Inject constructor(private val listener: OnClickListener?) :
    ListAdapter<SkinInfo, MySkinAdapter.MySkinViewHolder>(SkinInfoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySkinViewHolder {
        return MySkinViewHolder(
            ItemMySkinBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MySkinViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MySkinViewHolder(private val binding: ItemMySkinBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SkinInfo) {
            binding.apply {
                bindImage(imgFavoriteSkin, item.imageUrl)

                txtSkinTitle.text = item.skinTitle
                txtSkinKinds.text = item.skinKinds

                imgSelect.setOnClickListener {
                    listener?.onClickDelete(item.skinKinds)
                }

                cardView.setOnClickListener {
                    listener?.onClickItem(item)
                }
            }
        }
    }

    interface OnClickListener {
        fun onClickDelete(skinKinds: String)
        fun onClickItem(skinInfo: SkinInfo)
    }
}