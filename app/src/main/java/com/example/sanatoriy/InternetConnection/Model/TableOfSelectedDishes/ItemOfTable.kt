package com.example.sanatoriy.InternetConnection.Model.TableOfSelectedDishes

import com.example.sanatoriy.InternetConnection.Model.Catalogs.AnswerListofCatalogs
import com.example.sanatoriy.InternetConnection.Model.Catalogs.CatalogData
import com.example.sanatoriy.InternetConnection.Model.Dishes

class ItemOfTable( placeNumber: Int, typeOfFood:List<CatalogData>, typeOfDish:List<CatalogData>) {
    var selectedMenuOfPlace = HashMap<String,HashMap<String, Dishes?>>()
    init{
        typeOfFood.forEach { typeFood ->
            val dishList = HashMap<String,Dishes?>()
            typeOfDish.forEach { typeDish ->
                dishList[typeDish.id!!] = null
            }
            selectedMenuOfPlace[typeFood.id!!] = dishList
        }
    }
    var placeNumber:Int? = placeNumber
}