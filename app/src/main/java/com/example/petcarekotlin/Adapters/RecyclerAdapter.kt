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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.petcarekotlin.AddDetails
import com.example.petcarekotlin.Details
import com.example.petcarekotlin.Fragments.HomeFragment
import com.example.petcarekotlin.PetData.PetModel
import com.example.petcarekotlin.PetData.PetViewModel
import com.example.petcarekotlin.R
import com.example.roomapp.data.UserViewModel


class RecyclerAdapter constructor(val context:AddDetails): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private lateinit var petViewModel: PetViewModel
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

        if (newlist.neutered) {
            holder.neuteredtxt.text = "Neutered"
        }else{
            holder.neuteredtxt.text = " "
            holder.neuteredtxt.background=null
        }

        if (newlist.vacci) {
            holder.vaccitxt.text = "Vaccinated"
        }else{
            holder.vaccitxt.text = " "
            holder.vaccitxt.background=null
        }

        if (newlist.dogs) {
            holder.cattxt.text = "Friendly with dogs"
        }else{
            holder.dogtxt.text = " "
            holder.dogtxt.background=null

        }

        if (newlist.cats) {
            holder.cattxt.text = "Friendly with dogs"
        }else{
            holder.cattxt.text = ""
            holder.cattxt.background=null
        }

        holder.update.setOnClickListener {

            val i = Intent(it.context,AddDetails::class.java)
            i.putExtra("id",newlist.id.toString())
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
            i.putExtra("isEditMode",true)
            it.context.startActivity(i)

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

        holder.delete.setOnClickListener {
            petViewModel = ViewModelProvider(context).get(petViewModel::class.java)
            petViewModel.deletePet(newlist)
            Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT).show()

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
        val update:ImageView  = itemView.findViewById(R.id.update_btn)
        val delete:ImageView  = itemView.findViewById(R.id.delete_img)



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