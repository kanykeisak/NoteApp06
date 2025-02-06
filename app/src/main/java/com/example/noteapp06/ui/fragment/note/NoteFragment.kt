package com.example.noteapp06.ui.fragment.note

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp06.R
import com.example.noteapp06.model.data.models.NoteModel
import com.example.noteapp06.databinding.FragmentNoteBinding
import com.example.noteapp06.presenter.notes.NoteContract
import com.example.noteapp06.presenter.notes.NotePresenter
import com.example.noteapp06.ui.adapters.NoteAdapter
import com.example.noteapp06.ui.interfaces.OnClickItem
import com.google.android.material.navigation.NavigationView

class NoteFragment : Fragment(), OnClickItem, NoteContract.View{
    
    private lateinit var binding: FragmentNoteBinding
    private val noteAdapter: NoteAdapter = NoteAdapter(this, this)
    private var isGridLayout = false
    
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    
    private val presenter by lazy { NotePresenter(this) }
    
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
        presenter.loadNotes()
        
//        getData()
        
        drawerLayout = requireActivity().findViewById(R.id.noteFragment)
        navigationView = requireActivity().findViewById(R.id.navigation_view)
        
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
        
        binding.ivMenu.setOnClickListener {
            drawerLayout.openDrawer(navigationView)
        }
        
        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_note_app -> {
                    findNavController().navigate(R.id.authFragment)
                }
                R.id.nav_on_board -> {
                    findNavController().navigate(R.id.onBoardFragment)
                }
                R.id.nav_store_fragment -> {
                    findNavController().navigate(R.id.storeFragment)
                }
            }
            drawerLayout.closeDrawer(navigationView)
            true
        }
    }
    
//
    
    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        
        with(builder){
            setTitle("Удалить заметку?")
            setPositiveButton("Удалить") { dialog, _ ->
                presenter.deleteNote(noteModel)
            }
            setNegativeButton("Отмена"){ dialog, _ ->
                dialog.cancel()
            }
            show()
        }
        builder.create()
    }
    
    override fun onClick(noteModel: NoteModel) {
        val action = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment(noteModel.id)
        findNavController().navigate(action)
        
        
    }
    
    override fun showNotes(notes: List<NoteModel>) {
        noteAdapter.submitList(notes)
        noteAdapter.notifyDataSetChanged()
    }
    
    override fun showError(message: String) {
        Log.e("NotesFragment", message)
    }
}