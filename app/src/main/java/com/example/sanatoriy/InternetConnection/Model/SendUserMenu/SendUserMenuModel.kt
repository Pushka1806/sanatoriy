package com.example.sanatoriy.InternetConnection.Model.SendUserMenu

import com.google.gson.annotations.SerializedName


data class SendUserMenuModel (

  @SerializedName("menu"        ) var menu        : Menu? = Menu(),
  @SerializedName("placeNumber" ) var placeNumber : Int?  = null

)