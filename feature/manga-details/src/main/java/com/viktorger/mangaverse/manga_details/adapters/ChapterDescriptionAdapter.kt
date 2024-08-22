package com.viktorger.mangaverse.manga_details.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.viktorger.mangaverse.core.model.MangaDetails
import com.viktorger.mangaverse.manga_details.databinding.ChapterDescriptionBinding

class ChapterDescriptionAdapter :
    RecyclerView.Adapter<ChapterDescriptionAdapter.ChapterDescriptionViewHolder>() {
    var mangaDescription: MangaDetails? = null
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ChapterDescriptionViewHolder(private val binding: ChapterDescriptionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mangaDescription: MangaDetails) = with(binding) {
            tvChapterdescriptionDescription.text = mangaDescription.description
            tvChapterdescriptionGenres.text = mangaDescription.genres
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChapterDescriptionViewHolder {
        val binding = ChapterDescriptionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ChapterDescriptionViewHolder(binding)
    }

    override fun getItemCount(): Int = mangaDescription?.let { 1 } ?: 0

    override fun onBindViewHolder(holder: ChapterDescriptionViewHolder, position: Int) {
        mangaDescription?.let {
            holder.bind(it)
        }
    }

}
