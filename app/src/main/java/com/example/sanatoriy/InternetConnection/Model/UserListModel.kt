package com.example.sanatoriy.InternetConnection.Model

import com.example.sanatoriy.UserMenuModel

class UserListModel(private val table: Int)
 {   var userList = listOf<UserMenuModel>(UserMenuModel(tableNumber = table, tablePlaceNumber = 0),
                                          UserMenuModel(tableNumber = table, tablePlaceNumber = 1),
                                          UserMenuModel(tableNumber = table, tablePlaceNumber = 2),
                                          UserMenuModel(tableNumber = table, tablePlaceNumber = 3),
                                          UserMenuModel(tableNumber = table, tablePlaceNumber = 4),
                                          UserMenuModel(tableNumber = table, tablePlaceNumber = 5))
}