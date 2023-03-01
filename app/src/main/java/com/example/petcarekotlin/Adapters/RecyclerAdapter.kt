package com.example.petcarekotlin.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petcarekotlin.PetData.PetModel
import com.example.petcarekotlin.R

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var petlist = emptyList<PetModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_info,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newlist = petlist[position]
        holder.name.text = newlist.name
        holder.species.text = newlist.species
        holder.breed.text = newlist.breed
        holder.size.text = newlist.size

    }

    override fun getItemCount(): Int {
            return petlist.size
    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val name  = itemView.findViewById<TextView>(R.id.dogname_txt)
        val species  = itemView.findViewById<TextView>(R.id.species_txt)
        val breed  = itemView.findViewById<TextView>(R.id.breed_name_txt)
        val size  = itemView.findViewById<TextView>(R.id.size_txt)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(petlist:List<PetModel>){
        this.petlist = petlist
        notifyDataSetChanged()
    }
}