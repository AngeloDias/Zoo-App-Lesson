package br.com.training.android.zooapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_animal_info.*

class AnimalInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_info)

        val bundle: Bundle? = intent.extras
        val nameExtra = bundle!!.getString("name")
        val descriptionExtra = bundle.getString("description")
        val imageExtra = bundle.getInt("image")

        textViewAnimalName.text = nameExtra
        textViewDescription.text = descriptionExtra

        imageViewAnimal.setImageResource(imageExtra)
    }

}
