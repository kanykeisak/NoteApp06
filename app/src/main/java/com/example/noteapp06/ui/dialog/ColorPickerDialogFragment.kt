package com.example.noteapp06.ui.dialog

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.noteapp06.databinding.DialogColorPickerBinding

class ColorPickerDialogFragment(
    private val onColorSelected: (Int) -> Unit
) : DialogFragment() {
    
    private lateinit var binding: DialogColorPickerBinding
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogColorPickerBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Устанавливаем обработчики для кнопок цветов
        binding.colorYellow.setOnClickListener {
            onColorSelected(Color.YELLOW)
            dismiss()
        }
        binding.colorBlue.setOnClickListener {
            onColorSelected(Color.BLUE)
            dismiss()
        }
        binding.colorPurple.setOnClickListener {
            onColorSelected(Color.MAGENTA)
            dismiss()
        }
        binding.colorGreen.setOnClickListener {
            onColorSelected(Color.GREEN)
            dismiss()
        }
    }
}
