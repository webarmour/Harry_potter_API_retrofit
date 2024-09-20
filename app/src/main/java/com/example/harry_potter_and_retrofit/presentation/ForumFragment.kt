package com.example.harry_potter_and_retrofit.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harry_potter_and_retrofit.databinding.FragmentForumBinding
import com.example.harry_potter_and_retrofit.presentation.firebaseUtils.DatabaseUtils

class ForumFragment : Fragment() {

    private val viewModel: ForumViewModel by viewModels()

    private var _binding: FragmentForumBinding? = null
    private val binding get() = _binding!!
    private lateinit var adatper : ForumAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentForumBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imButton.setOnClickListener {
            val text = binding.etMessage.text.toString()
            if (text.isNotBlank()){
                viewModel.sendTextToFirebaseDb(text, getDbUtils())
            }
            binding.etMessage.text.clear()
        }
        binding.etMessage.addTextChangedListener(
            viewModel.textWatcherForEditText(binding.imButton)
        )
        setRcView()
    }

    private fun setRcView() {
        adatper = ForumAdapter(getDbUtils().getFirebaseRecyclerOptions())
        val layoutManager = LinearLayoutManager(getMainActivity())
        layoutManager.stackFromEnd = true
        binding.rcView.adapter = adatper
        binding.rcView.layoutManager = layoutManager
        adatper.registerAdapterDataObserver(
            MyScrollToBottomObserver(binding.rcView,layoutManager,adatper)
        )
    }

    override fun onResume() {
        super.onResume()
        adatper.startListening()
    }

    override fun onPause() {
        super.onPause()
        adatper.stopListening()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getMainActivity() = requireActivity() as MainActivity
    private fun getDbUtils() =  getMainActivity().databaseUtils


    companion object {
        fun newInstance() = ForumFragment()
    }

}



