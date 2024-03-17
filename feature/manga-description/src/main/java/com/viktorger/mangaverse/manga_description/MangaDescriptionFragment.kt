package com.viktorger.mangaverse.manga_description

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.bumptech.glide.Glide
import com.viktorger.mangaverse.core.model.ResultModel
import com.viktorger.mangaverse.manga_description.adapters.ChapterDescriptionAdapter
import com.viktorger.mangaverse.manga_description.adapters.ChaptersAdapter
import com.viktorger.mangaverse.manga_description.databinding.FragmentMangaDescriptionBinding
import com.viktorger.mangaverse.manga_description.di.MangaDescriptionComponent
import com.viktorger.mangaverse.manga_description.di.MangaDescriptionComponentProvider
import com.viktorger.mangaverse.manga_description.navigation.MangaDescriptionNavigation
import javax.inject.Inject

class MangaDescriptionFragment : Fragment() {

    private lateinit var mangaDescriptionComponent: MangaDescriptionComponent

    @Inject
    lateinit var mangaDescriptionNavigation: MangaDescriptionNavigation

    private var _binding: FragmentMangaDescriptionBinding? = null
    private val binding: FragmentMangaDescriptionBinding get() = _binding!!

    @Inject
    lateinit var vmFactory: MangaDescriptionViewModelFactory
    private val vm: MangaDescriptionViewModel by viewModels { vmFactory }

    private val descriptionAdapter: ChapterDescriptionAdapter by lazy {
        ChapterDescriptionAdapter()
    }
    private val chaptersAdapter: ChaptersAdapter by lazy {
        ChaptersAdapter {
            mangaDescriptionNavigation.navigateToChapter(it) { action ->
                findNavController().navigate(action)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mangaDescriptionComponent =
            (requireActivity().applicationContext as MangaDescriptionComponentProvider)
                .provideMangaDescriptionComponent()
        mangaDescriptionComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMangaDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbDescription.setupWithNavController(findNavController())
        binding.tbDescription.title = ""

        val mangaUrl = mangaDescriptionNavigation.getMangaUrl(requireArguments())
        vm.getMangaDescription(mangaUrl)
        vm.getMangaChapters(mangaUrl)

        initListeners()
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

    private fun initListeners() {
        vm.detailsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ResultModel.Loading -> {

                }

                is ResultModel.Success -> {
                    with(binding) {
                        tbDescription.title = it.data.title
                        Glide
                            .with(requireContext())
                            .load(it.data.imageUrl)
                            .into(ivDescription)

                        descriptionAdapter.mangaDetails = it.data
                    }
                }

                is ResultModel.Error -> {
                    Log.d(MangaDescriptionFragment::class.simpleName, "${it.e.message}")
                }
            }
        }

        vm.chaptersLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ResultModel.Loading -> {

                }

                is ResultModel.Success -> {
                    chaptersAdapter.submitList(it.data)
                }

                is ResultModel.Error -> {
                    Log.d(MangaDescriptionFragment::class.simpleName, "${it.e.message}")
                }
            }
        }
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