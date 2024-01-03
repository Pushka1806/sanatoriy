package com.example.sanatoriy.data.user

import com.example.android.dagger.storage.Storage
import javax.inject.Inject
import javax.inject.Singleton

private const val REGISTERED_USER = "registered_user10"
@Singleton
class UserManager  @Inject constructor(
    private val storage: Storage,
    private val userComponentFactory: UserComponent.Factory,
    ) {

    var userComponent: UserComponent? = null
        private set

    val userId: String
        get() = storage.getString(REGISTERED_USER)

    fun isUserLoggedIn() = userComponent != null

    fun isUserRegistered() = storage.getString(REGISTERED_USER).isNotEmpty()

    fun registerUser(userID: String,) {
        storage.setString(REGISTERED_USER, userID)
    }

    fun logout() {
        // When the user logs out, we remove the instance of UserComponent from memory
        userComponent = null
    }

    fun unregister() {
        val username = storage.getString(REGISTERED_USER)
        storage.setString(REGISTERED_USER, "")
        logout()
    }

     fun userJustLoggedIn() {
        // When the user logs in, we create a new instance of UserComponent
        userComponent = userComponentFactory.create()
    }



//    fun printUser(user_number: Int): String {
//        var text: String =  ""
//        text += "user $user_number\n"
//        var listOfFoods = userTable.userList[user_number].menu!!.typeOfFoodIntakeItems
//        println(listOfFoods.size)
//        for(i in listOfFoods.indices){
//
//            for(j in listOfFoods[i].typeOfDishItems.indices){
//                text += "f($i)-d($j)- " + listOfFoods[i].typeOfDishItems[j].bludo!!.name + "\n"
//
//            }
//        }
//        return text
//    }
}