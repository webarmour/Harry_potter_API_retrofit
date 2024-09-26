package com.example.harry_potter_and_retrofit.presentation.ui.pagingfragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import com.example.harry_potter_and_retrofit.R
import com.example.harry_potter_and_retrofit.databinding.FragmentMainBinding
import com.example.harry_potter_and_retrofit.databinding.FragmentPagingBinding
import com.example.harry_potter_and_retrofit.presentation.ui.mainfragment.ProgressState
import kotlinx.coroutines.launch

class PagingFragment : Fragment() {

    private var _binding: FragmentPagingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PagingViewModel by viewModels()
    private lateinit var adapter : CharacterPagingListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.items.collect{
                    adapter.submitData(it)
                }
            }

        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                adapter.loadStateFlow.collect{
                  binding.appendIndicator.isVisible =  it.source.append is LoadState.Loading
                  binding.progressIndicator.isVisible =  it.source.prepend is LoadState.NotLoading
                }
            }

        }



    }
    fun initRcView(){
        adapter = CharacterPagingListAdapter()
        binding.rvPagingCharacters.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroy()
        _binding = null
    }



    companion object {
        fun newInstance() = PagingFragment()
    }
}