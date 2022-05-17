package com.example.myapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.CategoryEventAdapter
import com.example.myapplication.adapter.HomeScreenAdapter
import com.example.myapplication.adapter.PopularOrganizerAdapter
import com.example.myapplication.dataClass.CategoryEventModelClass
import com.example.myapplication.dataClass.HomeScreenModelClass
import com.example.myapplication.dataClass.PopularOrganizerModelClass
import com.example.myapplication.databinding.ActivityHomeScreenBinding

class HomeScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenBinding
    private val data = ArrayList<HomeScreenModelClass>()
    private val spinnerData: ArrayList<String> = ArrayList()
    private val categoryData = ArrayList<CategoryEventModelClass>()
    private val popularOrganizerData = ArrayList<PopularOrganizerModelClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contextThis = this

        with(binding) {
            customToolbar.tvTitleScreen.text = getString(R.string.title_home)
            eventRecyclerView.layoutManager = LinearLayoutManager(contextThis, LinearLayoutManager.HORIZONTAL, false)
            categoryRecyclerView.layoutManager = LinearLayoutManager(contextThis, LinearLayoutManager.HORIZONTAL, false)
            popularOrganizerRecyclerView.layoutManager = LinearLayoutManager(contextThis, LinearLayoutManager.HORIZONTAL, false)
        }

        addSpinnerItemData()
        addHomeScreenEventData()
        addCategoryTypesData()
        addPopularOrganizerData()

        binding.apply {
            eventRecyclerView.adapter = HomeScreenAdapter(data, contextThis, spinnerData)
            categoryRecyclerView.adapter = CategoryEventAdapter(categoryData)
            popularOrganizerRecyclerView.adapter = PopularOrganizerAdapter(popularOrganizerData)
        }
    }

    private fun addSpinnerItemData() {
        spinnerData.add(getString(R.string.spinner_item_going))
        spinnerData.add(getString(R.string.spinner_item_going))
        spinnerData.add(getString(R.string.spinner_item_going))
    }


    private fun addPopularOrganizerData() {
        popularOrganizerData.apply {
            add(
                PopularOrganizerModelClass(
                    getString(R.string.tv_popular_organizer_name_1),
                    R.drawable.organizer_image_one
                )
            )
            add(
                PopularOrganizerModelClass(
                    getString(R.string.tv_popular_organizer_name_2),
                    R.drawable.organizer_image_two
                )
            )
            add(
                PopularOrganizerModelClass(
                    getString(R.string.tv_popular_organizer_name_3),
                    R.drawable.organizer_image_three
                )
            )
            add(
                PopularOrganizerModelClass(
                    getString(R.string.tv_popular_organizer_name_1),
                    R.drawable.organizer_image_one
                )
            )
            add(
                PopularOrganizerModelClass(
                    getString(R.string.tv_popular_organizer_name_2),
                    R.drawable.organizer_image_two
                )
            )
            add(
                PopularOrganizerModelClass(
                    getString(R.string.tv_popular_organizer_name_3),
                    R.drawable.organizer_image_three
                )
            )
        }
    }

    private fun addCategoryTypesData() {
        categoryData.apply {
            add(
                CategoryEventModelClass(
                    getString(R.string.tv_food_event),
                    R.drawable.food_image_1
                )
            )
            add(
                CategoryEventModelClass(
                    getString(R.string.tv_music_event),
                    R.drawable.music_event_icon
                )
            )
            add(
                CategoryEventModelClass(
                   getString(R.string.tv_movie_image),
                    R.drawable.movie_event_icon
                )
            )
            add(
                CategoryEventModelClass(
                    getString(R.string.tv_food_event),
                    R.drawable.food_image_1
                )
            )
        }
    }

    private fun addHomeScreenEventData() {
        data.apply {
            add(
                HomeScreenModelClass(
                    getString(R.string.tv_event_name),
                    getString(R.string.tv_event_address)
                )
            )
            add(
                HomeScreenModelClass(
                    getString(R.string.tv_event_name),
                    getString(R.string.tv_event_address)
                )
            )
            add(
                HomeScreenModelClass(
                    getString(R.string.tv_event_name),
                    getString(R.string.tv_event_address)
                )
            )
        }
    }
}