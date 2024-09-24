package com.example.harry_potter_and_retrofit.presentation.dbfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.harry_potter_and_retrofit.databinding.FragmentWorkmanagerBinding

class WorkmanagerFragment : Fragment() {

    private val viewModel: WorkmanagerViewModel by viewModels {
        WorkmanagerViewModelFactory()
    }


    companion object {
        fun newInstance() = WorkmanagerFragment()
    }


    private var _binding: FragmentWorkmanagerBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWorkmanagerBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btNotify.setOnClickListener {
            viewModel.startService()
        }
        binding.btStop.setOnClickListener {
            viewModel.stopService()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}