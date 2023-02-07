package com.avicodes.beershop.presentation.ui.beer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.avicodes.beershop.data.models.BeerItem
import com.avicodes.beershop.databinding.ItemBeersBinding
import com.bumptech.glide.Glide

class BeersAdapter: RecyclerView.Adapter<BeersAdapter.ViewHolder>(){


    inner class ViewHolder(private val binding: ItemBeersBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(beerItem: BeerItem) {
            binding.run {
                Glide.with(ivBeers.context)
                    .load(beerItem.image_url)
                    .into(ivBeers)

                tvName.text = beerItem.name
                tvTagline.text = beerItem.tagline

                cvBeer.setOnClickListener {
                    onItemClickListener?.let {
                        it(beerItem)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBeersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    private var callback = object : DiffUtil.ItemCallback<BeerItem> (){
        override fun areItemsTheSame(oldItem: BeerItem, newItem: BeerItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: BeerItem, newItem: BeerItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    private var onItemClickListener: ((BeerItem)->Unit)?= null

    fun setOnItemClickListener(listener: (BeerItem) -> Unit) {
        onItemClickListener = listener
    }
}