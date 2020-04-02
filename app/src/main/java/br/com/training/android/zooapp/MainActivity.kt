package br.com.training.android.zooapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.view.*

class MainActivity : AppCompatActivity() {
    private var animals = ArrayList<Animal>()
    private var adapter: AnimalsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animals.add(Animal("Baboon", "It's like a monkey", R.drawable.baboon, false))
        animals.add(Animal("Bulldog", "It's a dog", R.drawable.bulldog, false))
        animals.add(Animal("Panda", "It's like a little bear", R.drawable.panda, true))
        animals.add(Animal("Swallow bird", "It's a beautiful bird", R.drawable.swallow_bird, false))
        animals.add(Animal("White tiger", "It's a very large and dangerous animal", R.drawable.white_tiger, true))
        animals.add(Animal("Zebra", "Lives in Africa", R.drawable.zebra, false))

        adapter = AnimalsAdapter(applicationContext, animals)

        listViewAnimals.adapter = adapter
    }

    // example of the way to remove from list and update its view
    fun deleteAnimal(index: Int) {
        animals.removeAt(index)
        adapter!!.notifyDataSetChanged()
    }

    // example of the way to add from list and update its view
    fun addAnimal(index: Int) {
        animals.add(animals[index])
        adapter!!.notifyDataSetChanged()
    }

    inner class AnimalsAdapter(private val appContext: Context, listOfAnimals: ArrayList<Animal>) : BaseAdapter() {
        private var animalsInAdapter = listOfAnimals

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val animal = animalsInAdapter[p0]
            val inflater =
                appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var myView = inflater.inflate(R.layout.animal_ticket, null)

            if(animal.isKiller!!) {
                myView = inflater.inflate(R.layout.animal_killer_ticket, null)
            }

            myView.textViewName.text = animal.name
            myView.textViewDescription.text = animal.description
            myView.imageViewAnimal.setImageResource(animal.image!!)
            myView.imageViewAnimal.setOnClickListener {
                val intent = Intent(appContext, AnimalInfo::class.java)

                intent.putExtra("name", animal.name)
                intent.putExtra("description", animal.description)
                intent.putExtra("image", animal.image!!)

                appContext.startActivity(intent)
            }

            return myView
        }

        override fun getItem(p0: Int): Any {
            return animalsInAdapter[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return animalsInAdapter.size
        }

    }

}
