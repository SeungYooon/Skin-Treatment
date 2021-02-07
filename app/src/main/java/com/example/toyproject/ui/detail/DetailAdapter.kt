package com.example.toyproject.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.toyproject.data.db.entities.Skins
import com.example.toyproject.databinding.ItemDetailBinding
import com.example.toyproject.util.extensions.SkinsDiffCallback
import com.example.toyproject.util.extensions.bindImage

class DetailAdapter : ListAdapter<Skins, DetailAdapter.DetailViewHolder>(SkinsDiffCallback()) {

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

        fun bind(item: Skins) {
            binding.apply {
                bindImage(imgSkin, item.imageUrl)

                txtSkinKinds.text = item.skinKinds
                txtDescription.text = item.description

                imgSkin.setOnClickListener {
                    it.findNavController().navigate(
                        DetailFragmentDirections.actionDetailFragmentToDetailCustomFragment(item)
                    )
                }
            }
        }
    }
}