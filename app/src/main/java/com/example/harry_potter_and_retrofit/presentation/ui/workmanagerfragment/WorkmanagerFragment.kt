package com.example.harry_potter_and_retrofit.presentation.ui.workmanagerfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.work.WorkInfo
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.databinding.FragmentWorkmanagerBinding
import com.example.harry_potter_and_retrofit.di.ContextModule
import com.example.harry_potter_and_retrofit.di.DaggerApplicationComponent
import com.example.harry_potter_and_retrofit.presentation.worker.CachingDataWorker

class WorkmanagerFragment : Fragment() {

    private val viewModel: WorkmanagerViewModel by viewModels {
        App.INSTANCE.appComponent.workmanagerViewModelFactory()
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

        viewModel.progressWorkInfoItems.observe(viewLifecycleOwner){
            if (!it.isNullOrEmpty()){
                it.forEach {
                    if (it.state == WorkInfo.State.RUNNING){
                        val progressValue = it.progress.getInt(CachingDataWorker.PROGRESS, 0)
                        binding.progressBar.progress = progressValue
                    }
                    binding.progressBar.isVisible = !it.state.isFinished
                }
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}