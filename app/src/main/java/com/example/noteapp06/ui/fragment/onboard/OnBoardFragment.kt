package com.example.noteapp06.ui.fragment.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.noteapp06.R
import com.example.noteapp06.databinding.FragmentOnBoardBinding
import com.example.noteapp06.ui.adapters.OnBoardAdapter

class OnBoardFragment : Fragment() {
    
    private lateinit var binding: FragmentOnBoardBinding
    
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
    }
    
    private fun initialize() {
        binding.viewPager.adapter = OnBoardAdapter(this)
        binding.dotsIndicator.attachTo(binding.viewPager)
    }
    
    private fun setupListeners() = with(binding.viewPager) {
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) = with(binding){
                super.onPageSelected(position)
                if(position == 2){
                    txtSkip.visibility = View.INVISIBLE
                }else{
                    txtSkip.visibility = View.VISIBLE
                    txtSkip.setOnClickListener{
                        setCurrentItem(currentItem + 2, true)
                    }
                }
            }
        })
    }
}