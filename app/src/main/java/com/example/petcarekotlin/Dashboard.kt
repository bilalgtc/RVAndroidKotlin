package com.example.petcarekotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.petcarekotlin.Fragments.AppointmentFragment
import com.example.petcarekotlin.Fragments.ExploreFragment
import com.example.petcarekotlin.Fragments.HomeFragment
import com.example.petcarekotlin.Fragments.ProfileFragment
import com.example.petcarekotlin.databinding.ActivityDashboardBinding

class Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment(HomeFragment())
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_icon -> {
                    setFragment(HomeFragment())
                    true
                }
                R.id.clock_icon -> {
                    setFragment(AppointmentFragment())
                    true
                }
                R.id.explore_icon -> {
                    setFragment(ExploreFragment())
                    true
                }
                R.id.profile_icon -> {
                    setFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }


    }

    fun setFragment(fragment: Fragment){
        val fragmentTransaction:FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container,fragment)
        fragmentTransaction.commit()
    }

}