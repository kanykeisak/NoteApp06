package com.example.noteapp06.ui.fragment.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteapp06.R
import com.example.noteapp06.databinding.FragmentNoteBinding
import com.example.noteapp06.utils.PreferenceHelper

class NoteFragment : Fragment() {
    
    private lateinit var binding: FragmentNoteBinding
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }
    
    private fun setupListener() = with(binding){
        val sharedPreferences = PreferenceHelper()
        sharedPreferences.unit(requireContext())
        btnSave.setOnClickListener{
            val et = etText.text.toString()
            sharedPreferences.text = et
            txtText.text = et
        }
        txtText.text = sharedPreferences.text
        
        
    }
    
}