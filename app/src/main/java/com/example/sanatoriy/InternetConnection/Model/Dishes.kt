package com.example.sanatoriy.InternetConnection.Model

import com.google.gson.annotations.SerializedName

data class Dishes(

  @SerializedName("id"                     ) var id: String?  = null,
  @SerializedName("name"                   ) var name: String?  = null,
  @SerializedName("weight"                 ) var weight: String?  = null,
  @SerializedName("protein"                ) var protein: Double?     = null,
  @SerializedName("fats"                   ) var fats: Double?     = null,
  @SerializedName("carbohydrates"          ) var carbohydrates: Double?     = null,
  @SerializedName("calories"               ) var calories: Double?  = null,
  @SerializedName("dieta"                  ) var dieta: List<String?>? = null,
  @SerializedName("isForKids"              ) var isForKids: Boolean? = null,
  @SerializedName("isDefaultBludo"         ) var isDefaultBludo: Boolean?  = null,
  @SerializedName("image"                  ) var image:String? = null,
  @SerializedName("typeOfDishId"           ) var typeOfDishId: String?  = null,
  @SerializedName("typeOfFoodIntakeItemId" ) var typeOfFoodIntakeItemId: String?  = null,
  @SerializedName("createdAt"              ) var createdAt: String?  = null,

)