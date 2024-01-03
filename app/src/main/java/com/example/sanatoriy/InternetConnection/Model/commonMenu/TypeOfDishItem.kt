package com.example.sanatoriy.InternetConnection.Model.commonMenu

import com.example.sanatoriy.InternetConnection.Model.Dishes
import com.google.gson.annotations.SerializedName


data class TypeOfDishItem (

  @SerializedName("typeOfDishItemId" ) var typeOfDishItemId : String?           = null,
  @SerializedName("dishes"           ) var dishes: List<Dishes> = listOf()

)