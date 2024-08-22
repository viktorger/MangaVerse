package com.viktorger.mangaverse.feature.read.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target
import com.viktorger.mangaverse.feature.read.databinding.ItemPageBinding

class PagesAdapter : RecyclerView.Adapter<PagesAdapter.PagesViewHolder>() {
    @SuppressLint("NotifyDataSetChanged")
    var pages: List<String> = listOf()
        set(value) {
            field = value
            itemCount = 1
            notifyDataSetChanged()
        }

    private var itemCount: Int = 0

    inner class PagesViewHolder(private val binding: ItemPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pageUrl: String) {
            Glide.with(binding.root)
                .load(pageUrl)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .thumbnail(
                    Glide.with(binding.root)
                        .load(pageUrl)
                        .sizeMultiplier(0.25f)
                )
                .override(Target.SIZE_ORIGINAL)
                .into(binding.ivItempage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagesViewHolder {
        val binding = ItemPageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagesViewHolder, position: Int) {
        holder.bind(pages[position])
    }

    override fun getItemCount(): Int = pages.size
}