package com.viktorger.mangaverse.feature.read

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.viktorger.mangaverse.core.model.LceState
import com.viktorger.mangaverse.feature.read.adapter.PagesAdapter
import com.viktorger.mangaverse.feature.read.databinding.FragmentReadBinding
import com.viktorger.mangaverse.feature.read.di.ReadComponent
import com.viktorger.mangaverse.feature.read.di.ReadComponentProvider
import com.viktorger.mangaverse.feature.read.navigation.ReadNavigation
import javax.inject.Inject

class ReadFragment : Fragment() {
    private lateinit var mangaDescriptionComponent: ReadComponent
    @Inject
    lateinit var readNavigation: ReadNavigation

    private var _binding: FragmentReadBinding? = null
    private val binding: FragmentReadBinding get() = _binding!!

    @Inject
    lateinit var vmFactory: ReadViewModelFactory
    private val vm: ReadViewModel by viewModels { vmFactory }

    private val pagesAdapter: PagesAdapter by lazy {
        PagesAdapter()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mangaDescriptionComponent =
            (requireActivity().applicationContext as ReadComponentProvider)
                .provideReadComponent()
        mangaDescriptionComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val chapterUrl = readNavigation.getChapterUrl(requireArguments())

        vm.getChapter(chapterUrl)
        Log.d(this::class.simpleName, chapterUrl)
        initListeners()
        initRecycler()

        findNavController()
    }

    private fun initRecycler() {
        binding.rvRead.adapter = pagesAdapter
    }

    private fun initListeners() {
        vm.chapterLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is LceState.Loading -> {

                }
                is LceState.Content -> {
                    pagesAdapter.pages = it.data.pagesUrls
                    it.data.pagesUrls.forEach { url ->
                        Log.d(this::class.simpleName, url)
                    }


                }
                is LceState.Error -> {
                    Log.e(this::class.simpleName, "${it.throwable.message}")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}