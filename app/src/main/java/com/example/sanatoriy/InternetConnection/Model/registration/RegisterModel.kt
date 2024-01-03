package com.example.sanatoriy.InternetConnection.Model.registration

import com.google.gson.annotations.SerializedName


data class RegisterModel (

  @SerializedName("status" ) var status : Int?              = null,
  @SerializedName("data"   ) var data   : RegisterData?             = RegisterData(),
  @SerializedName("paging" ) var paging : ArrayList<String> = arrayListOf()

)