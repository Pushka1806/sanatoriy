package com.example.sanatoriy.data.user

import com.example.sanatoriy.InternetConnection.Model.Catalogs.AnswerListofCatalogs
import com.example.sanatoriy.InternetConnection.Model.CommonMenuModel
import com.example.sanatoriy.InternetConnection.Model.GetUserMenu.UserData
import com.example.sanatoriy.InternetConnection.Model.TableOfSelectedDishes.UserTableOfSelectedDishes
import com.example.sanatoriy.InternetConnection.Model.UserListModel
import javax.inject.Singleton

@Singleton
class MenuSetting {
    var listTypeofFood : AnswerListofCatalogs? = null // модель каталог типов приёмов пищи
    var listTypeOfDish: AnswerListofCatalogs? = null // модель каталог типов блюд
    var globalMenu: CommonMenuModel? = null // модель принимает глобальное меню
    var userMenuFromResponse:List<UserData>? =null // модель которая принимает пользовательское меню
    var listOfPlaces = mutableListOf(0,1,2,3,4,5) // список номеров мест
    var tableOfSelectedDishes: UserTableOfSelectedDishes? = null // модель для заполнения выбранными блюдами
    var userMenu : UserListModel = UserListModel(11)
    var checkMenuDate: String = "000000" // дата просмотра меню
    var zakazMenuDate:String = "00000" //дата заказа меню

}