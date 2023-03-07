package com.example.petcarekotlin.Fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petcarekotlin.Adapters.RecyclerAdapter
import com.example.petcarekotlin.AddDetails
import com.example.petcarekotlin.Dashboard
import com.example.petcarekotlin.PetData.PetViewModel
import com.example.petcarekotlin.R

class HomeFragment : Fragment() {

    private lateinit var petViewModel: PetViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val v: View = inflater.inflate(R.layout.fragment_home, container, false)

        petViewModel = ViewModelProvider(this)[PetViewModel::class.java]


        val recyclerView = v.findViewById<RecyclerView>(R.id.dash_recyclerView)
        val adapter = RecyclerAdapter(v.context as Dashboard)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(v.context)
        petViewModel.readAllData.observe(viewLifecycleOwner) { user ->
            adapter.setData(user)
        }

        val htext = v.findViewById<TextView>(R.id.dash_textView)
        val plusbtn = v.findViewById<ImageView>(R.id.plus_btn)


        val txt = "What are you\nlooking for, Maria?"
        val s = SpannableString(txt)
        val fc = ForegroundColorSpan(Color.parseColor("#ffcf6f"))

        s.setSpan(fc, 25, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        htext.text = s

        plusbtn.setOnClickListener {
            startActivity(Intent(v.context, AddDetails::class.java))
        }

        return v
    }

}