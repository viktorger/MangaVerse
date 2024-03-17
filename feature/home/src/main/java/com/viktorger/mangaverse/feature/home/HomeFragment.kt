package com.viktorger.mangaverse.feature.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.viktorger.mangaverse.core.ui.adapters.MangaShortcutAdapter
import com.viktorger.mangaverse.feature.home.databinding.FragmentHomeBinding
import com.viktorger.mangaverse.feature.home.di.HomeComponent
import com.viktorger.mangaverse.feature.home.di.HomeComponentProvider
import com.viktorger.mangaverse.feature.home.navigation.HomeNavigation
import javax.inject.Inject


class HomeFragment : Fragment() {

    private lateinit var homeComponent: HomeComponent

    @Inject
    lateinit var homeNavigation: HomeNavigation

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    @Inject
    lateinit var vmFactory: HomeViewModelFactory
    private val vm: HomeViewModel by viewModels { vmFactory }

    private val adapter: MangaShortcutAdapter by lazy {
        MangaShortcutAdapter {
            homeNavigation.navigateToDescription(it) { action ->
                findNavController().navigate(action)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeComponent = (requireActivity().applicationContext as HomeComponentProvider)
            .provideHomeComponent()
        homeComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.doSmth()
        initRecycler()
        initListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initListeners() {
        vm.shortcutListLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun initRecycler() {
        binding.rvHome.adapter = adapter
    }

}