package com.example.sanatoriy.InternetConnection.Model.userMenu

import com.google.gson.annotations.SerializedName


data class UserTypeOfFoodIntakeItems(
  @SerializedName("typeOfFoodIntakeItemId" ) var typeOfFoodIntakeItemId: String?         = null,
  @SerializedName("typeOfDishItems"        ) var typeOfDishItems: MutableList<UserTypeOfDishItem> = mutableListOf()
)
