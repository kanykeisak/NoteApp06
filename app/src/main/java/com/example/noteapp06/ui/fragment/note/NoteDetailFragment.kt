package com.example.noteapp06.ui.fragment.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.noteapp06.App
import com.example.noteapp06.R
import com.example.noteapp06.data.models.NoteModel
import com.example.noteapp06.databinding.FragmentNoteDetailBinding
import java.util.Date
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class NoteDetailFragment : Fragment() {
    
    private lateinit var binding: FragmentNoteDetailBinding
    private var currentNote: NoteModel? = null
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        
//        val currentDate = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(Date())
//        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
//
//        binding.tvDate.text = currentDate
//        binding.tvTime.text = currentTime
    }
    
    
    private fun setupListener() = with(binding){
        btnAdd.setOnClickListener {
            val etTitle = etTitle.text.toString()
            val etDescription = etDescription.text.toString()
            val currentDate = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(Date())
            val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            
            
            App.appDatabase?.noteDao()?.insert(
                NoteModel(
                    etTitle,
                    etDescription,
                    currentDate,
                    currentTime
                )
            )
            findNavController().navigateUp()
        }
    }
}