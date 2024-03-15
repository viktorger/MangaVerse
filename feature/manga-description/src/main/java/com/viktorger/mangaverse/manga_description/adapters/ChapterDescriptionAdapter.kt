package com.viktorger.mangaverse.manga_description.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.viktorger.mangaverse.core.model.MangaDetails
import com.viktorger.mangaverse.manga_description.databinding.ChapterDescriptionBinding

class ChapterDescriptionAdapter :
    RecyclerView.Adapter<ChapterDescriptionAdapter.ChapterDescriptionViewHolder>() {
    var mangaDetails: MangaDetails? = null
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ChapterDescriptionViewHolder(private val binding: ChapterDescriptionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mangaDetails: MangaDetails) = with(binding) {
            tvChapterdescriptionDescription.text = mangaDetails.description
            tvChapterdescriptionGenres.text = mangaDetails.genres
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

    override fun getItemCount(): Int = mangaDetails?.let { 1 } ?: 0

    override fun onBindViewHolder(holder: ChapterDescriptionViewHolder, position: Int) {
        mangaDetails?.let {
            holder.bind(it)
        }
    }

}