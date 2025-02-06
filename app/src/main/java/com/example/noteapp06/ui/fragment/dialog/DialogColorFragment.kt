package com.example.noteapp06.ui.fragment.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteapp06.R
import com.example.noteapp06.databinding.FragmentDialogColorBinding

class DialogColorFragment(
    private val onColorSelected: (Int) -> Unit
) : Fragment() {
    
    private lateinit var binding: FragmentDialogColorBinding
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogColorBinding.inflate(inflater, container, false)
        return binding.root
    }
    
}


