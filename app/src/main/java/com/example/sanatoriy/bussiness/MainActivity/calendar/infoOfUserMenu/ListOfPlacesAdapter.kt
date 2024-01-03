package com.example.sanatoriy.bussiness.MainActivity.calendar.infoOfUserMenu

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sanatoriy.R

class ListOfPlacesAdapter (private val places: List<Int>, private  val enablePlaces : List<Int>) : RecyclerView
.Adapter<ListOfPlacesAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
           val placeNumber = itemView.findViewById<TextView>(R.id.place_number)!!
           val layoutNumber = itemView.findViewById<LinearLayout>(R.id.layoutOfPlaceNumber)!!
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.i("Adapter", "OnBind")
        holder.placeNumber.text = "Место № ${places[position]+ 1}"
        if(enablePlaces.contains(position +1)){
            holder.layoutNumber.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("place_number", places[position])
                holder.layoutNumber.findNavController().navigate(R.id.checkMenuFragment,bundle )
            }
        }
        else{
            holder.placeNumber.setBackgroundResource(R.drawable.border_disable_item_of_list)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.i("Adapter", "OnCreateViewHolder")
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_of_place, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        Log.i("Adapter", "getItemCount ${places.size}")
        return places.size
    }
}