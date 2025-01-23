package com.example.noteapp06.ui.fragment.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteapp06.R
import com.example.noteapp06.databinding.FragmentOnBoardPagerBinding

class OnBoardPagerFragment : Fragment() {
    
    private lateinit var binding: FragmentOnBoardPagerBinding
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardPagerBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }
    
    private fun initialize() = with(binding) {
        when(requireArguments().getInt(ARG_ONBOARD_POSITION)){
            0 ->{
                animation.setAnimation(R.raw.animation_one) // Подключаем первую анимацию
                txtOnboardTitle.text = "Удобство"
                txtOnboardDescription.text = "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно."
                btnStart.visibility = View.GONE
            }
            1 ->{
                animation.setAnimation(R.raw.animation_two)
                txtOnboardTitle.text = "Организация"
                txtOnboardDescription.text = "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время."
                btnStart.visibility = View.GONE
            }
            2 ->{
                animation.setAnimation(R.raw.animation_three)
                txtOnboardTitle.text = "Синхронизация"
                txtOnboardDescription.text = "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте."
                btnStart.visibility = View.VISIBLE
            }
        }
    }
    
    companion object{
        const val ARG_ONBOARD_POSITION = "onBoard"
    }
}