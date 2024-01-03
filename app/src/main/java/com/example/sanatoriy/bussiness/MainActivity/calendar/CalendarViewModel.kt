package com.example.sanatoriy.bussiness.MainActivity.calendar

import com.example.sanatoriy.data.user.UserDataRepositoriy
import javax.inject.Inject

class CalendarViewModel @Inject constructor(private val UDR: UserDataRepositoriy) {

    fun unregister(){
        UDR.unregister()
    }
}