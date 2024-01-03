package com.example.sanatoriy.bussiness.userDetail

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sanatoriy.InternetConnection.Model.Dishes
import com.example.sanatoriy.R

class UserDetailRecyclerAdapter (private val bludes: List<Dishes>) : RecyclerView
.Adapter<UserDetailRecyclerAdapter.MyViewHolder>()  {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var meal_id: String? = "0"
        val nameMeal: TextView = itemView.findViewById(R.id.name_item)
        val massMeal: TextView = itemView.findViewById(R.id.mass_item)
        val kbju: TextView = itemView.findViewById(R.id.kbju_item)
        val diet1: TextView = itemView.findViewById(R.id.diet1)
        val diet2: TextView = itemView.findViewById(R.id.diet2)
        val diet3: TextView = itemView.findViewById(R.id.diet3)
        val diet4: TextView = itemView.findViewById(R.id.diet4)
        val diet5: TextView = itemView.findViewById(R.id.diet5)
        val diet6: TextView = itemView.findViewById(R.id.diet6)
        val dietsMeal = arrayListOf<TextView>(diet1, diet2, diet3, diet4, diet5, diet6)
        val image: ImageView = itemView.findViewById(R.id.image_item)
//        val kids: TextView = itemView.findViewById(R.id.kids_item)
//        val dejurnoe_bludo: TextView = itemView.findViewById(R.id.dejurnoe_bludo_item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View
        Log.i("TAG", "RecyclerView")
        itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_menu_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.meal_id = bludes[position].id
        holder.nameMeal.text = bludes[position].name
        holder.massMeal.text = bludes[position].weight
        holder.kbju.text = readKbju(holder, position)
        var diets = bludes[position].dieta
        for (index in diets!!.indices) {
            holder.dietsMeal[index].text = diets[index]
        }
        holder.image.setImageResource(R.drawable.grecha)
    }

    override fun getItemCount() =  bludes.size

    fun readKbju(holder: MyViewHolder, position: Int): String {
        var kbju_item: String = " "
        kbju_item += bludes[position].calories
        kbju_item += "/"
        kbju_item += bludes[position].protein
        kbju_item += "/"
        kbju_item += bludes[position].fats
        kbju_item += "/"
        kbju_item += bludes[position].carbohydrates
        return kbju_item
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == bludes.size) R.layout.button else R.layout.user_menu_item
    }
}