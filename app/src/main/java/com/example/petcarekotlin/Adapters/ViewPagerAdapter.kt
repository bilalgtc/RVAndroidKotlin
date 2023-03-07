package com.example.petcarekotlin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.petcarekotlin.R

class ViewPagerAdapter(val context: Context): PagerAdapter() {



    private val images = arrayOf(R.drawable.illustration, R.drawable.illustration2, R.drawable.dog_illustration)
    private val headings = arrayOf( R.string.silde_heading1,
        R.string.silde_heading2,
        R.string.silde_heading3)

    private val description  = arrayOf(  R.string.slide_description1,
        R.string.slide_description2,
        R.string.slide_description3)



    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return  view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater:LayoutInflater  = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view :View = layoutInflater.inflate(R.layout.item,container,false)

        val img  = view.findViewById<ImageView>(R.id.item_imgview)
        val head  = view.findViewById<TextView>(R.id.textView2)
        val desc = view.findViewById<TextView>(R.id.textView3)

        img.setImageResource(images[position])
        head.setText(headings[position])
        desc.setText(description[position])

        container.addView(view,0)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager:ViewPager = container as ViewPager
        val view:View = `object` as View
        viewPager.removeView(view)
    }
}