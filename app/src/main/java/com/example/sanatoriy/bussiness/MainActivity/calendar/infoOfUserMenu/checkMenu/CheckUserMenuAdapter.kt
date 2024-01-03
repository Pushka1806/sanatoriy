package com.example.sanatoriy.bussiness.MainActivity.calendar.infoOfUserMenu.checkMenu

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sanatoriy.InternetConnection.Model.userMenu.UserTypeOfFoodIntakeItems
import com.example.sanatoriy.R

class CheckUserMenuAdapter (private val listTypeOfFood : MutableList<UserTypeOfFoodIntakeItems>) : RecyclerView
.Adapter<CheckUserMenuAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.i("Adapter", "OnBind")


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.i("Adapter", "OnCreateViewHolder")
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_of_place, parent, false)
        return MyViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        Log.i("Adapter", "getItemCount ${listTypeOfFood.size}")
        return listTypeOfFood.size
    }


}