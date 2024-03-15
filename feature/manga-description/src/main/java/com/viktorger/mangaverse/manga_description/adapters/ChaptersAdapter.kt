package com.viktorger.mangaverse.manga_description.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.viktorger.mangaverse.core.model.MangaChapter
import com.viktorger.mangaverse.manga_description.databinding.ItemChapterBinding

class ChaptersAdapter
    : ListAdapter<MangaChapter, ChaptersAdapter.ChapterViewHolder>(DiffUtilCallback()) {
    class DiffUtilCallback() : DiffUtil.ItemCallback<MangaChapter>() {
        override fun areItemsTheSame(oldItem: MangaChapter, newItem: MangaChapter): Boolean =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: MangaChapter, newItem: MangaChapter): Boolean =
            oldItem == newItem
    }

    class ChapterViewHolder(private val binding: ItemChapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mangaChapter: MangaChapter) = with(binding) {
            tvItemchapterVolume.text = mangaChapter.volume
            tvItemchapterChapter.text = mangaChapter.chapter
            tvItemchapterDate.text = mangaChapter.date
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
        holder.bind(getItem(position))

}