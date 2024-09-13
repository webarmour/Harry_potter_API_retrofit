package com.example.harry_potter_and_retrofit.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.harry_potter_and_retrofit.R
import com.example.harry_potter_and_retrofit.databinding.FragmentDbBinding
import kotlinx.coroutines.launch

class DbFragment : Fragment() {

    companion object {
        fun newInstance() = DbFragment()
    }

    private val viewModel: DbViewModel by viewModels()
    private var _binding : FragmentDbBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDbBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btAdd.setOnClickListener {
            viewModel.onBtnAdd()
        }
        binding.btUpdate.setOnClickListener {

        }
        binding.btDelete.setOnClickListener {

        }
        lifecycleScope.launch {
            viewModel.allCharacters.collect{
                binding.textView.text = it.joinToString(separator = "\r\n")
            }
        }




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}