package com.example.sanatoriy.InternetConnection.Model.Catalogs

import com.google.gson.annotations.SerializedName


data class AnswerListofCatalogs (

  @SerializedName("status" ) var status : Int?              = null,
  @SerializedName("data"   ) var data   : ArrayList<CatalogData>   = arrayListOf(),
  @SerializedName("paging" ) var paging : ArrayList<String> = arrayListOf()

)