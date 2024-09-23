package com.example.harry_potter_and_retrofit.presentation.characterlistfragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.harry_potter_and_retrofit.databinding.FragmentCharacterListBinding
import kotlinx.coroutines.launch

class CharacterListFragment : Fragment() {

    private var _binding : FragmentCharacterListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adatper: CharacterListAdapter

    private val viewModel: CharacterListViewModel by viewModels {
        CharacterListViewModelFactory()
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
    }



    private fun iniRcView(){
        adatper = CharacterListAdapter()
        binding.rcView.adapter = adatper
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}