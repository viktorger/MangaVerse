package com.viktorger.mangaverse.feature.read.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.viktorger.mangaverse.core.model.MangaChapter
import com.viktorger.mangaverse.feature.read.databinding.ItemPageBinding

class PagesAdapter : RecyclerView.Adapter<PagesAdapter.PagesViewHolder>() {
    /*class DiffUtilCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem
    }*/
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
                /*.listener(object:RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    @SuppressLint("NotifyDataSetChanged")
                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemCount = (itemCount + 1).coerceAtMost(pages?.pagesUrls?.size ?: 0)
                        notifyDataSetChanged()
                        return false
                    }
                })*/
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