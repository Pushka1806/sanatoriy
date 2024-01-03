package com.example.sanatoriy.InternetConnection.Model.GetUserMenu

import com.google.gson.annotations.SerializedName


data class GetUserMenuModel (

  @SerializedName("status" ) var status : Int?              = null,
  @SerializedName("data"   ) var data   : List<UserData>   = mutableListOf(),
  @SerializedName("paging" ) var paging : List<String> = mutableListOf()

)