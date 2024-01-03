package com.example.sanatoriy.InternetConnection.Model.userMenu

import com.example.sanatoriy.InternetConnection.Model.Dishes
import com.google.gson.annotations.SerializedName


data class UserTypeOfDishItem (
  @SerializedName("typeOfDishItemId" ) var typeOfDishItemId : String?           = null,
  @SerializedName("dishes"           ) var bludo           : Dishes? = null
)