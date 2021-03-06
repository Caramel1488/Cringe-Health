package com.example.technopa.diet.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.technopa.Classes.Dieta

import com.example.technopa.R
import com.example.technopa.databinding.ItemDietLayoutBinding

class DietListAdapter(
    private val onItemClick: (position: Int) -> Unit
) : ListAdapter<Dieta, DietListAdapter.Holder>(DiffUtilCallBack()) {

    private val lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemDietLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding, onItemClick, parent)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))

    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Dieta>() {
        override fun areItemsTheSame(oldItem: Dieta, newItem: Dieta): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Dieta, newItem: Dieta): Boolean {
            return oldItem == newItem
        }
    }

    class Holder(
        private val binding: ItemDietLayoutBinding,
        val onItemClick: (position: Int) -> Unit,
        val parent: ViewGroup
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick(position)
            }
        }

        fun bind(item: Dieta) {
            binding.itemDietTitle.text = item.title
            binding.itemDietKkal.text = "${item.kaloriipd} ккал в день"
            binding.itemDietPriems.text = parent.resources.getQuantityString(
                R.plurals.priemsPishi,
                item.priemPishiList!!.size,
                item.priemPishiList!!.size
            )

            binding.itemDietTitle.animation = AnimationUtils.loadAnimation(parent.context,R.anim.animation)
            binding.itemDietKkal.animation = AnimationUtils.loadAnimation(parent.context,R.anim.animation)
            binding.itemDietPriems.animation = AnimationUtils.loadAnimation(parent.context,R.anim.animation)
            // itemView.itemPersonalDietTextView.text = item.name
        }
    }


}
