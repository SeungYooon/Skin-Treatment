package com.example.toyproject.ui.skin

import android.graphics.Typeface
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.toyproject.R
import com.example.toyproject.data.entities.SkinType
import com.example.toyproject.databinding.ItemSkinBinding
import com.example.toyproject.extensions.GlideApp
import javax.inject.Inject

class SkinAdapter @Inject constructor(
    private val listener: OnClickListener?
) : ListAdapter<SkinType, SkinAdapter.SkinViewHolder>(DIFF_CALLBACK) {

    init {
        setHasStableIds(true)
    }

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
        val item = currentList[position]
        holder.bind(item)
    }

    inner class SkinViewHolder(private val binding: ItemSkinBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SkinType) {
            binding.apply {
                GlideApp.with(itemView.context).load(item.skinImg)
                    .placeholder(R.drawable.bubble_filled)
                    .error(R.drawable.ch_network_error_illust)
                    .into(imgSkin)
                item.typeName.let { txtSkinName.setText(it) }
                item.skinDescription.let { txtDescription.setText(it) }

                imgSkin.setOnClickListener {
                    listener?.onClick(binding.imgSkin, item)
                }

                SpannableStringBuilder(txtSkinName.text).apply {
                    setSpan(object : ClickableSpan() {
                        override fun onClick(view: View) {
                            Typeface.BOLD
                            listener?.onClick(binding.imgSkin, item)
                        }
                    }, 0, length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
                }.let {
                    txtSkinName.movementMethod = LinkMovementMethod.getInstance()
                    txtSkinName.setText(it, TextView.BufferType.SPANNABLE)
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