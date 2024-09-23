package com.example.harry_potter_and_retrofit.presentation.dbfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.harry_potter_and_retrofit.databinding.FragmentDbBinding
import kotlinx.coroutines.launch

class DbFragment : Fragment() {

    private val viewModel: DbViewModel by viewModels {
        DbViewModelFactory()
    }



    companion object {
        fun newInstance() = DbFragment()
    }


    private var _binding: FragmentDbBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        binding.btDelete.setOnClickListener {
            viewModel.btDelete()
        }
        lifecycleScope.launch {
            viewModel.characters.collect{
                binding.textView.text = it.joinToString(separator = "\r\n")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}