package com.example.sanatoriy.bussiness.MainActivity.calendar.menuList.SelectedDishes

import com.example.sanatoriy.InternetConnection.Model.Dishes
import com.example.sanatoriy.InternetConnection.Model.SendUserMenu.SendUserMenuModel
import com.example.sanatoriy.data.user.UserDataRepositoriy
import javax.inject.Inject

class ListOfPlacesSelectedDishesViewModel @Inject constructor(private val UDR: UserDataRepositoriy ) {

    private var modelForSend: List<SendUserMenuModel> ?= null

    val sendModel :List<SendUserMenuModel>?
        get() = modelForSend

    val enablePlaces : List<Int>
        get() = getEnablePlacesOfSelectedDishes()

    private fun getEnablePlacesOfSelectedDishes():List<Int>{ // номера начинаются с 1
        var enablePlaces = mutableListOf<Int>()
        //UDR.userTableOfSelectedDishes!!.itemsOfTable.forEach {  Log.i("SelectedDishes", it.placeNumber.toString() + " |||||" + it.selectedMenuOfPlace.toString()) }
        sendModel!!.forEach { enablePlaces.add(it.placeNumber!!.toInt()) }
        return enablePlaces
    }

    fun getUserId():String{
        return UDR.userId

    }
    fun getListOfPlaceNumbers():List<Int>{
        return UDR.getListOfPlaceNumbers()
    }
    fun getSelectedDishesForCheckUserMenu(placeNumber:Int):HashMap<String,List<Dishes>>{
        return UDR.getHashMapSelectedDishesOfPlaceNumber(placeNumber)
    }

    fun convertMenuForSend() {
        modelForSend = UDR.getMenuForSend()
    }




}