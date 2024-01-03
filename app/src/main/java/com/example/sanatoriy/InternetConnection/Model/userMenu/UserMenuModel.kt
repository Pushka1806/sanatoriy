package com.example.sanatoriy

import com.example.sanatoriy.InternetConnection.Model.userMenu.UserMenuItemModel
import com.google.gson.annotations.SerializedName


data class UserMenuModel(

    @SerializedName("menu"             ) var menu             : UserMenuItemModel? = UserMenuItemModel(),
    @SerializedName("tableNumber"      ) var tableNumber      : Int?  = null,
    @SerializedName("tablePlaceNumber" ) var tablePlaceNumber : Int?  = null

)