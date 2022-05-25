package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.adapter.OnBoardingScreenAdapter
import com.example.myapplication.dataClass.OnBoardingModelClass
import com.example.myapplication.databinding.ActivityOnBoardingScreenBinding
import kotlin.collections.ArrayList

class OnBoardingScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingScreenBinding
    private val imageData = ArrayList<OnBoardingModelClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardView.setBackgroundResource(R.drawable.card_view_design)

        addData()
        binding.apply {
            viewPager.adapter = OnBoardingScreenAdapter(imageData)
        }

        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback ()  {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tvHeader.text = imageData[position].header
                binding.tvSubHeader.text = imageData[position].subHeader
            }
        })
    }

    private fun addData() {
       imageData.apply {
            add(
                OnBoardingModelClass(
                    R.drawable.onboarding_screen_1,
                    getString(R.string.tv_header_first_page),
                    getString(R.string.tv_sub_header_first_page)
                )
            )

           add(
               OnBoardingModelClass(
                   R.drawable.onboarding_screen_image_2,
                   getString(R.string.tv_header_second_page),
                   getString(R.string.tv_sub_header_second_page)
               )
           )

           add(
               OnBoardingModelClass(
                   R.drawable.onboarding_screen_image_3,
                   getString(R.string.tv_header_third_page),
                   getString(R.string.tv_sub_header_third_page)
               )
           )
       }
    }
}