package com.example.sanatoriy.InternetConnection.Model.TableOfSelectedDishes

import com.example.sanatoriy.InternetConnection.Model.Catalogs.AnswerListofCatalogs

class UserTableOfSelectedDishes( listOfPlace : List<Int> ,typeOfFood: AnswerListofCatalogs, typeOfDish: AnswerListofCatalogs) {
    var itemsOfTable = mutableListOf<ItemOfTable>()
    init{

        listOfPlace.forEach { placeNumber -> itemsOfTable.add(ItemOfTable(placeNumber, typeOfFood.data, typeOfDish.data)) }

    }


}