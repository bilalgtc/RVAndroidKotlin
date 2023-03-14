package com.example.petcarekotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.petcarekotlin.Adapters.ViewPagerAdapter
import com.example.petcarekotlin.databinding.ActivityIntroductionBinding

class Introduction : AppCompatActivity() {
    private lateinit var binding: ActivityIntroductionBinding
    private lateinit var viewadapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroductionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewadapter = ViewPagerAdapter(this)

        binding.viewpager.adapter = viewadapter
        binding.dots.attachTo(binding.viewpager)

        binding.nextBtn.setOnClickListener {
            if (getItem(0) < 2) {
                binding.viewpager.setCurrentItem(getItem(1), true)
            } else {
                val i = Intent(applicationContext, LandingPage::class.java)
                startActivity(i)
                finish()
            }
        }

        binding.viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position == 2) {
                    binding.nextBtn.text = "Get Started"
                } else {
                    binding.nextBtn.text = "Next"
                }
            }

            override fun onPageSelected(position: Int) {

            }

        })
    }

    fun getItem(position: Int): Int {
        return binding.viewpager.currentItem + position
    }
}