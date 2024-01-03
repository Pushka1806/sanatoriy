package com.example.sanatoriy.InternetConnection.Model.GetUserMenu

import com.google.gson.annotations.SerializedName


data class UserData (

  @SerializedName("menu"        ) var menu        : UserMenu?   = UserMenu(),
  @SerializedName("placeNumber" ) var placeNumber : String? = null

)