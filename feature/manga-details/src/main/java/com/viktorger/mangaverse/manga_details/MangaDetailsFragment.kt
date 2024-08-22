package com.viktorger.mangaverse.manga_details

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.bumptech.glide.Glide
import com.viktorger.mangaverse.core.model.LceState
import com.viktorger.mangaverse.core.ui.elm.ElmBaseFragment
import com.viktorger.mangaverse.manga_details.adapters.ChapterDescriptionAdapter
import com.viktorger.mangaverse.manga_details.adapters.ChaptersAdapter
import com.viktorger.mangaverse.manga_details.databinding.FragmentMangaDetailsBinding
import com.viktorger.mangaverse.manga_details.di.MangaDescriptionComponent
import com.viktorger.mangaverse.manga_details.di.MangaDescriptionComponentProvider
import com.viktorger.mangaverse.manga_details.navigation.MangaDetailsNavigation
import money.vivid.elmslie.android.renderer.androidElmStore
import money.vivid.elmslie.core.store.Store
import javax.inject.Inject


class MangaDetailsFragment :
    ElmBaseFragment<
            MangaDetailsEvent,
            MangaDetailsEffect,
            MangaDetailsState>(R.layout.fragment_manga_details) {

    private lateinit var mangaDescriptionComponent: MangaDescriptionComponent

    @Inject
    lateinit var mangaDetailsNavigation: MangaDetailsNavigation

    private var _binding: FragmentMangaDetailsBinding? = null
    private val binding: FragmentMangaDetailsBinding get() = _binding!!

    private val descriptionAdapter: ChapterDescriptionAdapter by lazy {
        ChapterDescriptionAdapter()
    }
    private val chaptersAdapter: ChaptersAdapter by lazy {
        ChaptersAdapter {
            mangaDetailsNavigation.navigateToChapter(it) { action ->
                findNavController().navigate(action)
            }
        }
    }

    @Inject
    lateinit var elmStoreFactory: MangaDetailsStoreFactory

    override val store: Store<
            MangaDetailsEvent,
            MangaDetailsEffect,
            MangaDetailsState> by androidElmStore { elmStoreFactory.create() }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mangaDescriptionComponent =
            (requireActivity().applicationContext as MangaDescriptionComponentProvider)
                .provideMangaDescriptionComponent()
        mangaDescriptionComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mangaUrl = mangaDetailsNavigation.getMangaUrl(requireArguments())
        if (savedInstanceState == null) {
            store.accept(MangaDetailsEvent.Ui.LoadDetails(mangaUrl))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMangaDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbDescription.setupWithNavController(findNavController())
        binding.tbDescription.title = ""

        initRecycler()
        // changeNavigateUpButtonColor()
    }

    private fun initRecycler() {
        val concatAdapter = ConcatAdapter(descriptionAdapter, chaptersAdapter)
        binding.rvDescription.adapter = concatAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun render(state: MangaDetailsState) {
        when (state.mangaDetails) {
            is LceState.Content -> {
                val details = state.mangaDetails.data
                binding.tbDescription.title = details.title
                Glide
                    .with(requireContext())
                    .load(details.imageUrl)
                    .into(binding.ivDescription)

                descriptionAdapter.mangaDescription = details
                chaptersAdapter.submitList(details.mangaChaptersShortcuts)
            }

            is LceState.Error -> Unit
            LceState.Loading -> Unit
        }
    }

    override fun handleEffect(effect: MangaDetailsEffect): Unit {

    }

    private fun changeNavigateUpButtonColor() {
        binding.ablDescription.addOnOffsetChangedListener { _, verticalOffset ->
            if (binding.ctlDescription.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(
                    binding.ctlDescription
                )
            ) {
                binding.tbDescription.navigationIcon?.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        com.viktorger.core.designsystem.R.color.black
                    ), PorterDuff.Mode.SRC_ATOP
                )
            } else {
                binding.tbDescription.navigationIcon?.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        com.viktorger.core.designsystem.R.color.white
                    ), PorterDuff.Mode.SRC_ATOP
                )
            }
        }
    }
}