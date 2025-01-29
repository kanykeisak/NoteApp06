package com.example.noteapp06.ui.fragment.note

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
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
    
    private var noteId: Int = -1
    
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
        updateNote()
        
        val currentDate = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(Date())
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

        binding.tvDate.text = currentDate
        binding.tvTime.text = currentTime
        
//        binding.ivColor.setOnClickListener {
//            // Открываем ColorPickerDialogFragment
//            val dialog = ColorPickerDialogFragment { selectedColor ->
//                // Устанавливаем выбранный цвет в качестве фона или другого элемента
//                binding.root.setBackgroundColor(selectedColor)
//            }
//            dialog.show(parentFragmentManager, "colorPicker")
//        }
    }
    
    private fun updateNote() {
        arguments?.let {args ->
            noteId = args.getInt("noteId", -1)
        }
        if(noteId != -1){
            val id = App.appDatabase?.noteDao()?.getById(noteId)
            id?.let { noteModel ->
                binding.etTitle.setText(noteModel.title)
                binding.etDescription.setText(noteModel.description)
            }
        }
    }
    
    
    private fun setupListener() = with(binding){
        btnAdd.setOnClickListener {
            val etTitle = etTitle.text.toString()
            val etDescription = etDescription.text.toString()
//            val currentDate = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(Date())
//            val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            
            if(noteId != -1){
                val updateNote = NoteModel(etTitle, etDescription)
                updateNote.id = noteId
                App.appDatabase?.noteDao()?.update(updateNote)
            }else{
                App.appDatabase?.noteDao()?.insert(NoteModel(etTitle, etDescription))
            }
            findNavController().navigateUp()
        }
    }
}