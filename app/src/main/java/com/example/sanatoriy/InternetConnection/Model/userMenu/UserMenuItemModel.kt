package com.example.sanatoriy.InternetConnection.Model.userMenu

import com.google.gson.annotations.SerializedName

class UserMenuItemModel(
    @SerializedName("typeOfFoodIntakeItems" ) var typeOfFoodIntakeItems : MutableList<UserTypeOfFoodIntakeItems> = mutableListOf()
) 