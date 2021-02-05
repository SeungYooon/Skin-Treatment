package com.example.toyproject.ui.skin

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.toyproject.databinding.ItemSkinBinding
import com.example.toyproject.domain.model.SkinType
import com.example.toyproject.util.extensions.bindImage
import javax.inject.Inject

class SkinAdapter @Inject constructor(
    private val listener: OnClickListener?
) : ListAdapter<SkinType, SkinAdapter.SkinViewHolder>(DIFF_CALLBACK) {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkinViewHolder {
        return SkinViewHolder(
            ItemSkinBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SkinViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SkinViewHolder(private val binding: ItemSkinBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SkinType) {
            binding.apply {
                bindImage(imgSkin, item.skinImg)
                txtSkinName.setText(item.typeName)
                txtDescription.setText(item.skinDescription)

                imgSkin.setOnClickListener {
                    listener?.onClick(imgSkin, item)
                }
            }
        }
    }

    interface OnClickListener {
        fun onClick(imageView: ImageView, skinType: SkinType)
    }

    companion object {
        private val DIFF_CALLBACK =
            AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<SkinType>() {
                override fun areItemsTheSame(oldItem: SkinType, newItem: SkinType): Boolean =
                    oldItem.typeName == newItem.typeName

                override fun areContentsTheSame(oldItem: SkinType, newItem: SkinType): Boolean =
                    oldItem == newItem
            }).build()
    }
}