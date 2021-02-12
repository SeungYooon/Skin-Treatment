package com.example.toyproject.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.toyproject.data.db.entities.SkinInfo
import com.example.toyproject.databinding.ItemDetailBinding
import com.example.toyproject.util.extensions.SkinInfoDiffCallback
import com.example.toyproject.util.extensions.bindImage
import javax.inject.Inject

class DetailAdapter @Inject constructor(private val onItemClicked: (SkinInfo) -> Unit) :
    ListAdapter<SkinInfo, DetailAdapter.DetailViewHolder>(SkinInfoDiffCallback()) {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            ItemDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DetailViewHolder(private val binding: ItemDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SkinInfo) {
            binding.apply {
                bindImage(imgSkin, item.imageUrl)

                txtSkinKinds.text = item.skinKinds
                txtDescription.text = item.description

                imgSkin.setOnClickListener {
                    onItemClicked(item)
                }
            }
        }
    }
}