package com.viktorger.mangaverse.core.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.viktorger.mangaverse.core.model.MangaShortcut
import com.viktorger.mangaverse.core.ui.databinding.ItemMangaBinding

class MangaShortcutAdapter : ListAdapter<MangaShortcut, MangaShortcutAdapter.ViewHolder>(MangaShortcutDiffUtil()) {

    private class MangaShortcutDiffUtil : DiffUtil.ItemCallback<MangaShortcut>() {
        override fun areItemsTheSame(oldItem: MangaShortcut, newItem: MangaShortcut): Boolean =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: MangaShortcut, newItem: MangaShortcut): Boolean =
            oldItem == newItem
    }

    class ViewHolder(
        private val binding: ItemMangaBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MangaShortcut) {
            binding.tvItemMangaTitle.text = item.title
            binding.tvItemMangaType.text = item.genres

            Glide.with(binding.root)
                .load(item.imageUrl)
                .into(binding.ivItemManga)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMangaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))
}