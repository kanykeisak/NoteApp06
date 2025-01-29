package com.example.noteapp06.ui.fragment.store

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp06.databinding.FragmentStoreBinding
import com.example.noteapp06.ui.adapters.StoreAdapter
import com.google.firebase.Firebase
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore

class StoreFragment : Fragment() {
    
    private lateinit var binding: FragmentStoreBinding
    private val storeAdapter = StoreAdapter()
    private val db = Firebase.firestore
    private lateinit var query: Query
    private lateinit var listener: ListenerRegistration
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
        observeStore()
    }
    
    
    private fun initialize(){
        binding.rvStore.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = storeAdapter
        }
    }
    
    private fun setupListeners() = with(binding){
        btnStore.setOnClickListener {
            val user = hashMapOf(
                "name" to etStore.text.toString()
            )
            db.collection("user").add(user).addOnCompleteListener {  }
            
            etStore.text.clear()
        }
    }
    
    private fun observeStore() {
        query = db.collection("user")
        listener = query.addSnapshotListener{ value, error ->
            if(error != null){
                return@addSnapshotListener
            }
            
            value?.let {data ->
                val dataList = mutableListOf<String>()
                for (doc in data.documents){
                    val valueStore = doc.getString("name")
                    valueStore?.let {value ->
                        dataList.add(value)
                    }
                }
                storeAdapter.submitList(dataList)
            }
        }
    }
    
}