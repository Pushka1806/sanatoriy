package com.example.sanatoriy.data.user

import android.util.Log
import com.example.sanatoriy.InternetConnection.Model.Catalogs.AnswerListofCatalogs
import com.example.sanatoriy.InternetConnection.Model.CommonMenuModel
import com.example.sanatoriy.InternetConnection.Model.Dishes
import com.example.sanatoriy.InternetConnection.Model.GetUserMenu.UserData
import com.example.sanatoriy.InternetConnection.Model.SendUserMenu.Menu
import com.example.sanatoriy.InternetConnection.Model.SendUserMenu.SendUserMenuModel
import com.example.sanatoriy.InternetConnection.Model.SendUserMenu.TypeOfFoodIntakeItems
import com.example.sanatoriy.InternetConnection.Model.TableOfSelectedDishes.ItemOfTable
import com.example.sanatoriy.InternetConnection.Model.TableOfSelectedDishes.UserTableOfSelectedDishes
import com.example.sanatoriy.InternetConnection.Model.UserListModel
import javax.inject.Inject

//@LoggedUserScope
class UserDataRepositoriy @Inject constructor(
    private val user: UserManager,
    private val menu: MenuSetting
    ) {

    val userId: String
        get() = userManager.userId

    val userManager: UserManager
        get() = user

    val userTableOfSelectedDishes: UserTableOfSelectedDishes?
        get() = menu.tableOfSelectedDishes

    val userMenu: UserListModel
        get() = menu.userMenu

    val globalmenu: CommonMenuModel?
        get() = menu.globalMenu

    fun unregister(){
        user.unregister()
    }



    fun setGlobalMenu(_menu: CommonMenuModel?) {
        menu.globalMenu = _menu
    }
    fun setUserMenuFromResponse(data: List<UserData>){
        menu.userMenuFromResponse = data
    }

    val typeOfFood: AnswerListofCatalogs?
        get() = menu.listTypeofFood

    val typeOfDish: AnswerListofCatalogs?
        get() = menu.listTypeOfDish

    fun setTypeOfFood(catalog: AnswerListofCatalogs) {
        menu.listTypeofFood = catalog
    }
    
    
    fun setTypeOfDish(catalog: AnswerListofCatalogs) {
        menu.listTypeOfDish = catalog
    }

    fun getDishes(idTypeOfFood: Int, idTypeOfDish: Int): List<Dishes> {
        return menu.globalMenu!!.data.menu.typeOfFoodIntakeItems[idTypeOfFood].typeOfDishItems[idTypeOfDish].dishes
    }

    fun registerUser(userId: String) {
        user.registerUser(userId)
    }

    fun getUserMenuOfPlaceNumber(placeNumber: Int): ItemOfTable {
        return userTableOfSelectedDishes!!.itemsOfTable[placeNumber]
    }

    fun getEnablePlacesForShow() : List<Int>{
        var enablePlaces = mutableListOf<Int>()
        menu.userMenuFromResponse!!.forEach{
            enablePlaces.add(it.placeNumber!!.toInt())

        }
        return  enablePlaces
    }

//    fun getPlacesOfSelectedDishes():List<Int> {
//        var enablePlaces = mutableListOf<Int>()
//        menu.tableOfSelectedDishes!!.itemsOfTable.forEach{
//            enablePlaces.add(it.placeNumber!!)
//        }
//        return enablePlaces
//    }

    fun getNumberFromCatalog(catalog: AnswerListofCatalogs?, value: String): Int? {
        var field = 0
        catalog!!.data.forEach {
            if (it.id == value) {
                return field
            }
        }
        return null
    }


    fun getMenuForSend(): List<SendUserMenuModel> {
        val modelForSend = mutableListOf<SendUserMenuModel>()
        userTableOfSelectedDishes!!.itemsOfTable.forEach{
//            Log.i("UserMenu", it.placeNumber.toString() + " " + it.selectedMenuOfPlace)
        }
        userTableOfSelectedDishes!!.itemsOfTable.forEach { user ->
            val listTypeOfFood = mutableListOf<TypeOfFoodIntakeItems>()
            val menuOfItem = user.selectedMenuOfPlace
            for (tFood in menuOfItem.keys) {
                val listOfDish = mutableListOf<String?>()
                val typeOfDishes = menuOfItem[tFood]
                typeOfDishes!!.forEach {
                    if (it.value != null) {
                        listOfDish.add(it.value!!.id)
                    }
                }
                if (listOfDish.isNotEmpty()) {
                    listTypeOfFood.add(TypeOfFoodIntakeItems(tFood, listOfDish))
                }
            }

            if (listTypeOfFood.isNotEmpty()) {
                modelForSend.add(
                    SendUserMenuModel(
                        Menu(typeOfFoodIntakeItems = listTypeOfFood),
                        user.placeNumber!!.plus(1)
                    )
                )
            }
        }
        return modelForSend
    }

    fun getCheckMenuDate():String{
        return menu.checkMenuDate
    }
    fun setCheckMenuDate(date:String){
        menu.checkMenuDate = date
    }

    fun getZakazMenuDate():String{
        return menu.zakazMenuDate
    }
    fun setZakazMenuDAte(date:String){
        menu.zakazMenuDate = date
    }

    fun getListOfPlaceNumbers():List<Int>{
        return menu.listOfPlaces
    }

    fun getHashMapDishesOfPlaceNumber(placeNumber: Int):HashMap<String,List<Dishes>>{
        val typeOfFoood = menu.userMenuFromResponse!![placeNumber].menu!!.typeOfFoodIntakeItems
        val dishList = hashMapOf<String,List<Dishes>>()
        typeOfFoood.forEach { typeFood ->
                             val listTypeOfDish = mutableListOf<Dishes>()
                             val title= findValueFromCatalog(menu.listTypeofFood!!,typeFood.typeOfFoodIntakeItemId!!)
                             typeFood.typeOfDishItems.forEach {typeDish->
                                                                listTypeOfDish.addAll(typeDish.dishes) }
                             dishList[title!!] = listTypeOfDish
        }
        return dishList

    }
    fun createSelectedUserMenuModel(){
        menu.tableOfSelectedDishes = UserTableOfSelectedDishes(menu.listOfPlaces, menu.listTypeofFood!! ,menu.listTypeOfDish!!)
    }

    fun getHashMapSelectedDishesOfPlaceNumber(placeNumber: Int):HashMap<String,List<Dishes>>{
        val dishList = hashMapOf<String,List<Dishes>>()
        val typeOfFood = menu.tableOfSelectedDishes!!.itemsOfTable[placeNumber].selectedMenuOfPlace
        typeOfFood.forEach { typeFood ->
            val listTypeOfDish = mutableListOf<Dishes>()
            val title= findValueFromCatalog(menu.listTypeofFood!!,typeFood.key)
            typeFood.value.forEach {typeDish->
                if(typeDish.value != null){
                    listTypeOfDish.add(typeDish.value!!)
                }
                 }
            dishList[title!!] = listTypeOfDish

        }

        return dishList

    }

    fun findValueFromCatalog(catalog: AnswerListofCatalogs, value: String): String? {

        catalog.data.forEach {
            if (it.id == value) {
                return it.description
            }
        }
        return null
    }






}
