package com.example.toyproject.ui.myskin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.toyproject.data.db.entities.Skins
import com.example.toyproject.databinding.ItemMySkinBinding
import com.example.toyproject.util.extensions.SkinsDiffCallback
import com.example.toyproject.util.extensions.bindImage
import javax.inject.Inject

class MySkinAdapter @Inject constructor(private val listener: OnClickListener?) :
    ListAdapter<Skins, MySkinAdapter.MySkinViewHolder>(SkinsDiffCallback()) {

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

        fun bind(item: Skins) {
            binding.apply {
                bindImage(imgFavoriteSkin, item.imageUrl)

                txtSkinTitle.text = item.skinTitle
                txtSkinKinds.text = item.skinKinds

                imgSelect.setOnClickListener {
                    listener?.onClick(item.skinKinds)
                }

                cardView.setOnClickListener {
                    it.findNavController().navigate(
                        MySkinFragmentDirections.actionMySkinFragmentToDetailCustomFragment(item)
                    )
                }
            }
        }
    }

    interface OnClickListener {
        fun onClick(skinKinds: String)
    }
}