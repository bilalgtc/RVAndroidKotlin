package com.example.petcarekotlin.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.petcarekotlin.AddDetails
import com.example.petcarekotlin.Details
import com.example.petcarekotlin.MainActivity
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
        holder.image.setImageBitmap(getImage(newlist.image))


        if (newlist.gender =="male")

        holder.gender.text = "male"
        else{
            holder.gender.text = "female"
        }

        if (newlist.neutered.equals(true)) {
            holder.neuteredtxt.text = "Neutered"
        }else{
            holder.neuteredtxt.text = " "
            holder.neuteredtxt.background=null
        }

        if (newlist.vacci.equals(true)) {
            holder.vaccitxt.text = "Vaccinated"
        }else{
            holder.vaccitxt.text = " "
            holder.vaccitxt.background=null
        }

        if (newlist.dogs.equals(true)) {
            holder.cattxt.text = "Friendly with dogs"
        }else{
            holder.dogtxt.text = " "
            holder.dogtxt.background=null

        }

        if (newlist.cats.equals(true)) {
            holder.cattxt.text = "Friendly with dogs"
        }else{
            holder.cattxt.text = ""
            holder.cattxt.background=null
        }

        holder.image.setOnClickListener {
            val i = Intent(it.context,Details::class.java)
            i.putExtra("name",newlist.name)
            i.putExtra("species",newlist.species)
            i.putExtra("breed",newlist.breed)
            i.putExtra("size",newlist.size)
            i.putExtra("gender",newlist.gender)
            i.putExtra("image",newlist.image)

            i.putExtra("neutered",newlist.neutered.toString())
            i.putExtra("vacci",newlist.vacci.toString())
            i.putExtra("dogs",newlist.dogs.toString())
            i.putExtra("cats",newlist.cats.toString())
            i.putExtra("child",newlist.child.toString())
            i.putExtra("children",newlist.childern.toString())
            it.context.startActivity(i)

        }


    }

    override fun getItemCount(): Int {
            return petlist.size
    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val name:TextView  = itemView.findViewById(R.id.dogname_txt)
        val species:TextView  = itemView.findViewById(R.id.species_txt)
        val breed:TextView = itemView.findViewById(R.id.breed_name_txt)
        val size: TextView = itemView.findViewById(R.id.size_txt)
        val image:ImageView = itemView.findViewById(R.id.main_img)
        val gender:TextView = itemView.findViewById(R.id.gender_txt)
        val neuteredtxt:TextView = itemView.findViewById(R.id.neutered_txt)
        val vaccitxt:TextView = itemView.findViewById(R.id.vacci_txt)
        val dogtxt:TextView = itemView.findViewById(R.id.friwithdogs_txt)
        val cattxt:TextView = itemView.findViewById(R.id.friwithcats_txt)



    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(petlist:List<PetModel>){
        this.petlist = petlist
        notifyDataSetChanged()
    }

    fun getImage(images: ByteArray?): Bitmap? {
        return images?.let { BitmapFactory.decodeByteArray(images, 0, it.size) }
    }
}