package com.example.sanatoriy.InternetConnection.Model.registration

import com.google.gson.annotations.SerializedName


data class RegisterData (

  @SerializedName("id"         ) var id         : String? = null,
  @SerializedName("tableId"    ) var tableId    : String? = null,
  @SerializedName("itemNumber" ) var itemNumber : String? = null,
  @SerializedName("createdAt"  ) var createdAt  : String? = null

)