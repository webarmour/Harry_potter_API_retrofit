package com.example.harry_potter_and_retrofit.presentation.ui.characterlistfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adatper: CharacterListAdapter

    @Inject
    lateinit var VMFactory: CharacterListViewModelFactory


    private val viewModel: CharacterListViewModel by viewModels {
        VMFactory
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniRcView()
        fillListAdapter()


        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


    }

    private fun fillListAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.characterList.collect {
                adatper.submitList(it)
            }
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isLoading.collect() {
                binding.swipeRefreshLayout.isRefreshing = it
            }
        }
    }


    private fun iniRcView() {
        adatper = CharacterListAdapter()
        binding.rcView.adapter = adatper
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}