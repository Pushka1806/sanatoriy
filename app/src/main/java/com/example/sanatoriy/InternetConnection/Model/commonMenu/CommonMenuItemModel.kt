package com.example.sanatoriy.InternetConnection.Model.commonMenu

import com.google.gson.annotations.SerializedName

class CommonMenuItemModel(
    @SerializedName("typeOfFoodIntakeItems" ) var typeOfFoodIntakeItems : List<CommonTypeOfFoodIntakeItems> = listOf()
)
