package com.example.toyproject.ui.today

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.toyproject.data.db.entities.Skins
import com.example.toyproject.databinding.ItemTodaySkinBinding
import com.example.toyproject.util.extensions.SkinsDiffCallback
import com.example.toyproject.util.extensions.bindImage
import javax.inject.Inject

class TodaySkinAdapter @Inject constructor(
    private val listener: OnClickListener?
) : ListAdapter<Skins, TodaySkinAdapter.TodaySkinViewHolder>(SkinsDiffCallback()) {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodaySkinViewHolder {
        return TodaySkinViewHolder(
            ItemTodaySkinBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodaySkinViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TodaySkinViewHolder(private val binding: ItemTodaySkinBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Skins) {
            binding.apply {
                bindImage(imgSkin, item.imageUrl)

                txtSkinTitle.text = item.skinTitle
                txtSkinKinds.text = item.skinKinds

                imgSelect.setOnClickListener {
                    listener?.onClick(item)
                }

                imgSkin.setOnClickListener {
                    it.findNavController().navigate(
                        TodaySkinFragmentDirections.actionTodaySkinFragmentToDetailCustomFragment(
                            item
                        )
                    )
                }
            }
        }
    }

    interface OnClickListener {
        fun onClick(skins: Skins)
    }
}

