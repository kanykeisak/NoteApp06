package com.example.noteapp06.ui.fragment.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.Lottie.initialize
import com.example.noteapp06.App
import com.example.noteapp06.R
import com.example.noteapp06.databinding.FragmentNoteBinding
import com.example.noteapp06.ui.adapters.NoteAdapter
import com.example.noteapp06.utils.PreferenceHelper

class NoteFragment : Fragment() {
    
    private lateinit var binding: FragmentNoteBinding
    private val noteAdapter: NoteAdapter = NoteAdapter()
    private var isGridLayout = false
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
        getData()
    }
    
    private fun initialize() {
        binding.rvNote.apply{
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
    }
    
    private fun setupListener() = with(binding){
        btnAction.setOnClickListener{
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }
        
        toggleLayout.setOnCheckedChangeListener { _, isChecked ->
            isGridLayout = isChecked
            if (isGridLayout) {
                rvNote.layoutManager = GridLayoutManager(requireContext(), 2)
            } else {
                rvNote.layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }
    
    private fun getData() {
        App.appDatabase?.noteDao()?.getAll()?.observe(viewLifecycleOwner){listModel ->
            noteAdapter.submitList(listModel)
        }
    }
    
    
}