package com.viktorger.mangaverse.manga_description.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.viktorger.mangaverse.core.model.MangaChapterShortcut
import com.viktorger.mangaverse.manga_description.databinding.ItemChapterBinding

class ChaptersAdapter(private val onClick: (String) -> Unit)
    : ListAdapter<MangaChapterShortcut, ChaptersAdapter.ChapterViewHolder>(DiffUtilCallback()) {
    class DiffUtilCallback() : DiffUtil.ItemCallback<MangaChapterShortcut>() {
        override fun areItemsTheSame(oldItem: MangaChapterShortcut, newItem: MangaChapterShortcut): Boolean =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: MangaChapterShortcut, newItem: MangaChapterShortcut): Boolean =
            oldItem == newItem
    }

    class ChapterViewHolder(private val binding: ItemChapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mangaChapterShortcut: MangaChapterShortcut, onClick: (String) -> Unit) = with(binding) {
            binding.root.setOnClickListener {
                onClick(mangaChapterShortcut.url)
            }
            tvItemchapterVolume.text = mangaChapterShortcut.volume
            tvItemchapterChapter.text = mangaChapterShortcut.chapter
            tvItemchapterDate.text = mangaChapterShortcut.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        val binding = ItemChapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ChapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) =
        holder.bind(getItem(position), onClick)

}