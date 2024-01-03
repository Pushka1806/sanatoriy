package com.example.sanatoriy.InternetConnection.Model.GetUserMenu

import com.example.sanatoriy.InternetConnection.Model.commonMenu.CommonTypeOfFoodIntakeItems
import com.google.gson.annotations.SerializedName


data class UserMenu (

  @SerializedName("typeOfFoodIntakeItems" ) var typeOfFoodIntakeItems : ArrayList<CommonTypeOfFoodIntakeItems> = arrayListOf(),
  @SerializedName("targetDate"            ) var targetDate            : String?                          = null

)