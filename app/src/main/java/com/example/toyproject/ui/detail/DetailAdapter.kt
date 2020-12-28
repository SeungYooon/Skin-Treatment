package com.example.toyproject.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.example.toyproject.R
import com.example.toyproject.data.SkinInfo
import com.example.toyproject.databinding.ActivityMainBinding
import com.example.toyproject.databinding.DialogCustomBinding
import com.example.toyproject.databinding.ItemDetailBinding
import com.example.toyproject.extensions.GlideApp
import kotlinx.android.synthetic.main.dialog_custom.*
import javax.inject.Inject

class DetailAdapter @Inject constructor() :
    ListAdapter<SkinInfo, DetailAdapter.DetailViewHolder>(DIFF_CALLBACK) {

    init {
        setHasStableIds(true)
    }

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
        val item = currentList[position]
        holder.bind(item)
    }

    inner class DetailViewHolder(private val binding: ItemDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SkinInfo) {
            binding.apply {
                GlideApp.with(itemView.context).load(item.skinFace)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.drawable.ch_network_error_illust)
                    .into(imgSkin)
                txtSkinName.setText(item.skinTitle)

                cardView.setOnClickListener {
                    MaterialDialog(root.context).show {
                        customView(
                            view = DialogCustomBinding.inflate(
                                LayoutInflater.from(root.context),
                                null,
                                false
                            ).also {
                                GlideApp.with(context)
                                    .load("https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20201027140955_4_6_8.jpg")
                                    .into(it.ivDialog)
                                it.ivDialog.clipToOutline = true
                            }.root
                        )
                        cornerRadius(root.context.resources.getDimension(R.dimen.dialog_dimen))
                    }
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK =
            AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<SkinInfo>() {
                override fun areItemsTheSame(oldItem: SkinInfo, newItem: SkinInfo): Boolean =
                    oldItem.skinTitle == newItem.skinTitle

                override fun areContentsTheSame(oldItem: SkinInfo, newItem: SkinInfo): Boolean =
                    oldItem == newItem
            }).build()
    }
}