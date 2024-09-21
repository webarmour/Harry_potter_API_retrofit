package com.example.harry_potter_and_retrofit.presentation.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.harry_potter_and_retrofit.databinding.FragmentMainBinding
import com.example.harry_potter_and_retrofit.presentation.ProgressState
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.coroutines.launch

class MainFragment(
) : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory()
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



        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.character.collect {
                binding.tvName.text = it.character
                binding.tvHouse.text = it.hogwartsHouse
                binding.imView.load(it.image)

            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                if (it is ProgressState.Loading) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        binding.btRandom.setOnClickListener {
            FirebaseCrashlytics.getInstance().log("This log method with extra info")
            try {
                throw Exception("Kok issue")
            } catch(e: Exception){
                FirebaseCrashlytics.getInstance().recordException(e)
            }

//            viewModel.getRandomCharacter()

        }
        binding.tvHouse.setOnClickListener {

        }

        binding.tvHouse.setOnClickListener {

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}