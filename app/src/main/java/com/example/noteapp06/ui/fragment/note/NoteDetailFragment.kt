package com.example.noteapp06.ui.fragment.note

import android.graphics.Color
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import com.example.noteapp06.App
import com.example.noteapp06.R
import com.example.noteapp06.model.data.models.NoteModel
import com.example.noteapp06.databinding.FragmentNoteDetailBinding
import com.example.noteapp06.presenter.notedetail.NoteDetailContract
import com.example.noteapp06.presenter.notedetail.NoteDetailPresenter
import com.example.noteapp06.presenter.notes.NotePresenter
import java.util.Date
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class NoteDetailFragment : Fragment(), NoteDetailContract.View {
    
    private lateinit var binding: FragmentNoteDetailBinding
    private var currentNote: NoteModel? = null
    
    private val presenter by lazy { NoteDetailPresenter(this) }
    
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
            
            if(noteId != -1){
                val updateNote = NoteModel(etTitle, etDescription)
                updateNote.id = noteId
                presenter.updateNote(updateNote)
            }else{
                presenter.saveNote(NoteModel(title = etTitle, description = etDescription))
            }
            findNavController().navigateUp()
        }
    }
    
    override fun showNotes(notes: List<NoteModel>) {
    
    }
    
    override fun showError(message: String) {
    
    }
}