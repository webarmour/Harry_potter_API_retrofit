package com.example.harry_potter_and_retrofit.presentation.ui.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.databinding.FragmentMainBinding
import com.example.harry_potter_and_retrofit.di.ContextModule
import com.example.harry_potter_and_retrofit.di.DaggerApplicationComponent

class MainFragment() : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels{
        DaggerApplicationComponent.builder()
            .contextModule(ContextModule(App.INSTANCE))
            .build().mainFragmentViewModelFactory()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}