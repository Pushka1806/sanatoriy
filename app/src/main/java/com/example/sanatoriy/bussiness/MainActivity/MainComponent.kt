package com.example.sanatoriy.bussiness.MainActivity

import com.example.sanatoriy.bussiness.MainActivity.calendar.CalendarFragment
import com.example.sanatoriy.bussiness.MainActivity.calendar.infoOfUserMenu.ListOfPlacesFragment
import com.example.sanatoriy.bussiness.MainActivity.infoOfUserMenu.checkMenu.CheckSelectedDishesFragment
import com.example.sanatoriy.bussiness.SplashActivity.SplashActivity
import com.example.sanatoriy.data.user.LoggedUserScope
import dagger.Subcomponent
import dagger.android.ContributesAndroidInjector

@LoggedUserScope
@Subcomponent
interface MainComponent {
    // Factory to create instances of UserComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }




}