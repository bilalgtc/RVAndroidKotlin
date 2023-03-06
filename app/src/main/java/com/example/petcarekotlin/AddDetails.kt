package com.example.petcarekotlin

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.petcarekotlin.PetData.PetModel
import com.example.petcarekotlin.PetData.PetViewModel
import com.example.petcarekotlin.databinding.ActivityAddDetailsBinding
import java.io.ByteArrayOutputStream
import java.io.IOException


class AddDetails : AppCompatActivity() {

    private lateinit var binding: ActivityAddDetailsBinding
    private lateinit var petViewModel: PetViewModel

    private lateinit var gender: String

    private lateinit var thumbnail: Bitmap

    private var contentURI: Uri? = null
    private val GALLERY = 1

    private val CAMERA = 2
    private val stats = arrayOf(true, true, true, true, true, true)
    var isEditMode = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        cardStats()

        binding.addImage.setOnClickListener {
            imagePickDialog()
        }

        val id = intent.getStringExtra("id")
        val name = intent.getStringExtra("name")
        val species = intent.getStringExtra("species")
        val breed = intent.getStringExtra("breed")
        val size = intent.getStringExtra("size")
        val gender2 = intent.getStringExtra("gender")
        val images: ByteArray? = intent.getByteArrayExtra("image")

        val neutered = intent.getStringExtra("neutered")
        val vacci = intent.getStringExtra("vacci")
        val dog = intent.getStringExtra("dogs")
        val cat = intent.getStringExtra("cats")
        val child = intent.getStringExtra("child")
        val children = intent.getStringExtra("children")
        isEditMode = intent.getBooleanExtra("isEditMode", false)

        if(isEditMode) {


            binding.nameEd.setText(name)
            binding.speciesEd.setText(species)
            binding.breedEd.setText(breed)
            binding.sizeEd.setText(size)

            binding.circleImageView.setImageBitmap(getImage(images))

            if (gender2.equals("male")) {
                binding.cardView.background = getDrawable(R.drawable.card_blue_back)
                binding.maleImg.setColorFilter(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.white
                    )
                )
                binding.maleTxt.setTextColor(Color.WHITE)

                binding.cardView2.background = getDrawable(R.drawable.card_border)
                binding.cardView2.cardElevation = 0f
                binding.femaleImg.setColorFilter(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.black
                    )
                )
                binding.femaleTxt.setTextColor(Color.BLACK)
                binding.cardView.cardElevation = 10F
            } else {

                binding.cardView2.background = getDrawable(R.drawable.card_blue_back)
                binding.femaleImg.setColorFilter(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.white
                    )
                )
                binding.femaleTxt.setTextColor(Color.WHITE)

                binding.cardView.background = getDrawable(R.drawable.card_border)
                binding.cardView.cardElevation = 0f
                binding.maleImg.setColorFilter(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.black
                    )
                )
                binding.maleTxt.setTextColor(Color.BLACK)
                binding.cardView2.cardElevation = 10F
            }

            binding.s1.isChecked = neutered.equals("true")
            binding.s2.isChecked = vacci.equals("true")
            binding.s3.isChecked = dog.equals("true")
            binding.s4.isChecked = cat.equals("true")
            binding.s5.isChecked = child.equals("true")
            binding.s6.isChecked = children.equals("true")

        }else{
            Toast.makeText(applicationContext,"Not in editMode",Toast.LENGTH_SHORT).show()
        }

        petViewModel = ViewModelProvider(this)[PetViewModel::class.java]

        binding.submitBtn.setOnClickListener {


            if (isEditMode) {


                stats[0] = binding.s1.isChecked
                stats[1] = binding.s2.isChecked
                stats[2] = binding.s3.isChecked
                stats[3] = binding.s4.isChecked
                stats[4] = binding.s5.isChecked
                stats[5] = binding.s6.isChecked

                val name = binding.nameEd.text.toString()
                val species = binding.speciesEd.text.toString()
                val breed = binding.breedEd.text.toString()
                val size = binding.sizeEd.text.toString()


                val petModel = PetModel(
                    Integer.parseInt(id.toString()),
                    name,
                    species,
                    breed,
                    size,
                    getBytes(thumbnail),
                    gender,
                    stats[0],
                    stats[1],
                    stats[2],
                    stats[3],
                    stats[4],
                    stats[5]
                )
                petViewModel.updatePet(petModel)
                Toast.makeText(applicationContext,"Update Successful",Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, Dashboard::class.java))



            } else {


                stats[0] = binding.s1.isChecked
                stats[1] = binding.s2.isChecked
                stats[2] = binding.s3.isChecked
                stats[3] = binding.s4.isChecked
                stats[4] = binding.s5.isChecked
                stats[5] = binding.s6.isChecked

                val name = binding.nameEd.text.toString()
                val species = binding.speciesEd.text.toString()
                val breed = binding.breedEd.text.toString()
                val size = binding.sizeEd.text.toString()


                val petModel = PetModel(
                    0,
                    name,
                    species,
                    breed,
                    size,
                    getBytes(thumbnail),
                    gender,
                    stats[0],
                    stats[1],
                    stats[2],
                    stats[3],
                    stats[4],
                    stats[5]
                )
                petViewModel.addUser(petModel)
                Toast.makeText(applicationContext,"Add Successful",Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, Dashboard::class.java))

            }
        }


    }

    private fun imagePickDialog() {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Gallery", "Camera")
        pictureDialog.setItems(
            pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    fun choosePhotoFromGallary() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )

        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY) {
            if (data != null) {
                contentURI = data.data
                try {
                    thumbnail = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    binding.circleImageView.setImageBitmap(thumbnail)

                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this@AddDetails, "Failed!", Toast.LENGTH_SHORT).show()
                }

            }

        } else if (requestCode == CAMERA) {
            thumbnail = data!!.extras!!.get("data") as Bitmap
            binding.circleImageView.setImageBitmap(thumbnail)
        }


    }

    //for bitmap to byte
    fun getBytes(bitmap: Bitmap): ByteArray? {
        val stream = ByteArrayOutputStream()
        bitmap.compress(CompressFormat.PNG, 0, stream)
        return stream.toByteArray()
    }



    fun cardStats() {

        binding.maleTxt.setTextColor(Color.BLACK)
        binding.femaleTxt.setTextColor(Color.BLACK)

        binding.cardView.background = getDrawable(R.drawable.card_border)
        binding.cardView.cardElevation = 0f
        binding.maleImg.setColorFilter(ContextCompat.getColor(applicationContext, R.color.black))
        binding.cardView2.background = getDrawable(R.drawable.card_border)
        binding.cardView2.cardElevation = 0f
        binding.femaleImg.setColorFilter(ContextCompat.getColor(applicationContext, R.color.black))



        binding.cardView.setOnClickListener {
            binding.cardView.background = getDrawable(R.drawable.card_blue_back)
            binding.maleImg.setColorFilter(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.white
                )
            )
            binding.maleTxt.setTextColor(Color.WHITE)

            binding.cardView2.background = getDrawable(R.drawable.card_border)
            binding.cardView2.cardElevation = 0f
            binding.femaleImg.setColorFilter(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.black
                )
            )
            binding.femaleTxt.setTextColor(Color.BLACK)
            binding.cardView.cardElevation = 10F
            gender = "male"

        }

        binding.cardView2.setOnClickListener {
            binding.cardView2.background = getDrawable(R.drawable.card_blue_back)
            binding.femaleImg.setColorFilter(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.white
                )
            )
            binding.femaleTxt.setTextColor(Color.WHITE)

            binding.cardView.background = getDrawable(R.drawable.card_border)
            binding.cardView.cardElevation = 0f
            binding.maleImg.setColorFilter(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.black
                )
            )
            binding.maleTxt.setTextColor(Color.BLACK)
            binding.cardView2.cardElevation = 10F
            gender = "female"
        }


    }



    fun getImage(images: ByteArray?): Bitmap? {
        return images?.let { BitmapFactory.decodeByteArray(images, 0, it.size) }
    }

}