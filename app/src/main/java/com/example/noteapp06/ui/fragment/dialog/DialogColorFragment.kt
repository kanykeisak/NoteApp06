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
    
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // Устанавливаем обработчики для кнопок цветов
//        binding.colorYellow.setOnClickListener {
//            onColorSelected(Color.YELLOW)
//            dismiss()
//        }
//        binding.colorBlue.setOnClickListener {
//            onColorSelected(Color.BLUE)
//            dismiss()
//        }
//        binding.colorPurple.setOnClickListener {
//            onColorSelected(Color.MAGENTA)
//            dismiss()
//        }
//        binding.colorGreen.setOnClickListener {
//            onColorSelected(Color.GREEN)
//            dismiss()
//        }
//    }
}


