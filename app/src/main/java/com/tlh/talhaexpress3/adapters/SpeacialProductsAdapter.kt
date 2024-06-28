package com.tlh.talhaexpress3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tlh.talhaexpress3.data.Product
import com.tlh.talhaexpress3.databinding.SpecialRvItemBinding

class SpeacialProductsAdapter :
    RecyclerView.Adapter<SpeacialProductsAdapter.SpeacialProductsViewHolder>() {
    inner class SpeacialProductsViewHolder(private val binding: SpecialRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val diffCallback =
            object : DiffUtil.ItemCallback<Product>() { //makes recyclerview faster
                override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                    return oldItem == newItem
                }

            }
        val differ = AsyncListDiffer(SpeacialProductsAdapter(), diffCallback)//
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeacialProductsViewHolder {
        return SpeacialProductsViewHolder(
            LayoutInflater.from(parent.context)
        )
    }

    override fun getItemCount(): Int {
    }

    override fun onBindViewHolder(holder: SpeacialProductsViewHolder, position: Int) {
    }
}