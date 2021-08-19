package com.example.myapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.adapters.NoteAdapter
import com.example.myapplication.databinding.FragmentHistoryBinding
import com.example.myapplication.databinding.FragmentNotesBinding
import com.example.myapplication.databinding.NoteFragmentRecyclerItemBinding
import com.example.myapplication.model.AppState
import com.example.myapplication.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_notes.*


class NotesFragment : Fragment() {
    private lateinit var binding: FragmentNotesBinding
    private val viewModel: NoteViewModel by lazy {
        ViewModelProvider(this).get(NoteViewModel::class.java)
    }

    private val adapter: NoteAdapter by lazy {
        NoteAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteFragmentRecyclerView.adapter = adapter
        viewModel.noteLiveData.observe(viewLifecycleOwner, {render(it)})
        viewModel.getAllNotes()

    }

    private fun render(appState: AppState?) {
        when (appState) {
            is AppState.Success -> {
                binding.noteFragmentRecyclerView.visibility = View.VISIBLE
                binding.loadingLayout.visibility = View.GONE
                adapter.setData(appState.movieData)
            }
            is AppState.Loading -> {
                binding.noteFragmentRecyclerView.visibility = View.GONE
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.noteFragmentRecyclerView.visibility = View.VISIBLE
                binding.loadingLayout.visibility = View.GONE
                binding.noteFragmentRecyclerView.showSnackBar(
                        getString(R.string.error),
                        getString(R.string.reload),
                        {
                            viewModel.getAllNotes()
                        })
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = NotesFragment()
    }
}