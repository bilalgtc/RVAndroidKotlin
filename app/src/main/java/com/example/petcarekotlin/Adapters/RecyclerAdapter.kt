package com.example.petcarekotlin.Adapters

import android.annotation.SuppressLint
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
import com.example.petcarekotlin.Dashboard
import com.example.petcarekotlin.Details
import com.example.petcarekotlin.PetData.PetModel
import com.example.petcarekotlin.PetData.PetViewModel
import com.example.petcarekotlin.R


class RecyclerAdapter(val context: Dashboard) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private lateinit var petViewModel: PetViewModel
    private var petList = emptyList<PetModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_info, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newList = petList[position]
        holder.name.text = newList.name
        holder.species.text = newList.species
        holder.breed.text = newList.breed
        holder.size.text = newList.size
        holder.image.setImageBitmap(getImage(newList.image))


        if (newList.gender == "male")

            holder.gender.text = "ma    le"
        else {
            holder.gender.text = "female"
        }

        if (newList.neutered) {
            holder.neuteredtxt.text = "Neutered"
        } else {
            holder.neuteredtxt.text = " "
            holder.neuteredtxt.background = null
        }

        if (newList.vacci) {
            holder.vaccitxt.text = "Vaccinated"
        } else {
            holder.vaccitxt.text = " "
            holder.vaccitxt.background = null
        }

        if (newList.dogs) {
            holder.cattxt.text = "Friendly with dogs"
        } else {
            holder.dogtxt.text = " "
            holder.dogtxt.background = null

        }

        if (newList.cats) {
            holder.cattxt.text = "Friendly with dogs"
        } else {
            holder.cattxt.text = ""
            holder.cattxt.background = null
        }

        holder.update.setOnClickListener {

            val i = Intent(it.context, AddDetails::class.java)
            i.putExtra("id", newList.id.toString())
            i.putExtra("name", newList.name)
            i.putExtra("species", newList.species)
            i.putExtra("breed", newList.breed)
            i.putExtra("size", newList.size)
            i.putExtra("gender", newList.gender)
            i.putExtra("image", newList.image)

            i.putExtra("neutered", newList.neutered.toString())
            i.putExtra("vacci", newList.vacci.toString())
            i.putExtra("dogs", newList.dogs.toString())
            i.putExtra("cats", newList.cats.toString())
            i.putExtra("child", newList.child.toString())
            i.putExtra("children", newList.childern.toString())
            i.putExtra("isEditMode", true)
            it.context.startActivity(i)

        }

        holder.image.setOnClickListener {
            val i = Intent(it.context, Details::class.java)
            i.putExtra("name", newList.name)
            i.putExtra("species", newList.species)
            i.putExtra("breed", newList.breed)
            i.putExtra("size", newList.size)
            i.putExtra("gender", newList.gender)
            i.putExtra("image", newList.image)

            i.putExtra("neutered", newList.neutered.toString())
            i.putExtra("vacci", newList.vacci.toString())
            i.putExtra("dogs", newList.dogs.toString())
            i.putExtra("cats", newList.cats.toString())
            i.putExtra("child", newList.child.toString())
            i.putExtra("children", newList.childern.toString())
            it.context.startActivity(i)

        }

        holder.delete.setOnClickListener {
            petViewModel = ViewModelProvider(context)[PetViewModel::class.java]
            petViewModel.deletePet(newList)
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()

        }
    }

    override fun getItemCount(): Int {
        return petList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.dogname_txt)
        val species: TextView = itemView.findViewById(R.id.species_txt)
        val breed: TextView = itemView.findViewById(R.id.breed_name_txt)
        val size: TextView = itemView.findViewById(R.id.size_txt)
        val image: ImageView = itemView.findViewById(R.id.main_img)
        val gender: TextView = itemView.findViewById(R.id.gender_txt)
        val neuteredtxt: TextView = itemView.findViewById(R.id.neutered_txt)
        val vaccitxt: TextView = itemView.findViewById(R.id.vacci_txt)
        val dogtxt: TextView = itemView.findViewById(R.id.friwithdogs_txt)
        val cattxt: TextView = itemView.findViewById(R.id.friwithcats_txt)
        val update: ImageView = itemView.findViewById(R.id.update_btn)
        val delete: ImageView = itemView.findViewById(R.id.delete_img)


    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(petlist: List<PetModel>) {
        this.petList = petlist
        notifyDataSetChanged()
    }

    private fun getImage(images: ByteArray?): Bitmap? {
        return images?.let { BitmapFactory.decodeByteArray(images, 0, it.size) }
    }
}