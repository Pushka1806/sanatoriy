package com.example.sanatoriy.InternetConnection.Model.commonMenu


import com.google.gson.annotations.SerializedName


data class Data (

  @SerializedName("menu" ) var menu : CommonMenuItemModel = CommonMenuItemModel()

)