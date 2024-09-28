package com.example.harry_potter_and_retrofit.presentation.ui.forumfragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.databinding.FragmentForumBinding
import com.example.harry_potter_and_retrofit.di.ContextModule
import com.example.harry_potter_and_retrofit.di.DaggerApplicationComponent
import com.example.harry_potter_and_retrofit.presentation.ui.activities.MainActivity
import com.example.harry_potter_and_retrofit.presentation.utils.MyScrollToBottomObserver
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class ForumFragment : Fragment() {

    private val viewModel: ForumViewModel by viewModels {
        DaggerApplicationComponent.builder()
            .contextModule(ContextModule(App.INSTANCE))
            .build()
            .forumFragmentViewModelFactory()
    }

    private var _binding: FragmentForumBinding? = null
    private val binding get() = _binding!!
    private lateinit var adatper: ForumAdapter


    private val openDocLauncher = registerForActivityResult(
        object : ActivityResultContracts.OpenDocument() {
            override fun createIntent(context: Context, input: Array<String>): Intent {
                val intent = super.createIntent(context, input)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                return intent
            }
        }) { uri ->
        uri?.let {
            onImageSelected(it)
        }
    }

    private fun onImageSelected(uri: Uri) {
        Log.d("IAMGEE", "onImageSelected: $uri")
        val storage = Firebase.storage.getReference("/pictures")
        storage.putFile(uri).addOnCompleteListener{ task ->
            if (task.isSuccessful){
                binding.etMessage.hint = "Всё ништяк"
            } else {
                binding.etMessage.hint = "Всё плохо"
            }
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

    }


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
            if (text.isNotBlank()) {
                viewModel.sendTextToFirebaseDb(
                    text,
                   )
            }
            binding.etMessage.text.clear()
        }
        binding.etMessage.addTextChangedListener(
            viewModel.textWatcherForEditText(binding.imButton)
        )
        binding.imAttach.setOnClickListener {
            openDocLauncher.launch(arrayOf("image/*"))
        }
        setRcView()
    }

    private fun setRcView() {
        adatper = viewModel.getRecyclerAdapter()
        val layoutManager = LinearLayoutManager(getMainActivity())
        layoutManager.stackFromEnd = true
        binding.rcView.adapter = adatper
        binding.rcView.layoutManager = layoutManager
        adatper.registerAdapterDataObserver(
            MyScrollToBottomObserver(binding.rcView, layoutManager, adatper)
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


    companion object {
        fun newInstance() = ForumFragment()
    }

}



