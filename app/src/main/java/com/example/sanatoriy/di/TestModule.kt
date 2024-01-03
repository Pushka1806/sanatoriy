package com.example.sanatoriy.di

import androidx.transition.Visibility.Mode
import com.example.sanatoriy.bussiness.MainActivity.MainActivity
import com.example.sanatoriy.bussiness.MainActivity.calendar.CalendarFragment
import com.example.sanatoriy.bussiness.MainActivity.calendar.infoOfUserMenu.ListOfPlacesFragment
import com.example.sanatoriy.bussiness.MainActivity.calendar.infoOfUserMenu.checkMenu.CheckUserMenuFragment
import com.example.sanatoriy.bussiness.MainActivity.calendar.menuList.MenuListFragment
import com.example.sanatoriy.bussiness.MainActivity.calendar.menuList.SelectedDishes.ListOfPlacesSelectedDishesFragment
import com.example.sanatoriy.bussiness.MainActivity.infoOfUserMenu.checkMenu.CheckSelectedDishesFragment
import com.example.sanatoriy.bussiness.SplashActivity.SplashActivity
import com.example.sanatoriy.bussiness.registration.RegisterActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
public abstract class TestModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity():MainActivity

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity():SplashActivity

    @ContributesAndroidInjector
    abstract fun contributeRegistrationActivity():RegisterActivity

    @ContributesAndroidInjector
    abstract fun contributeCalendarFragment():CalendarFragment

    @ContributesAndroidInjector
    abstract fun contributeMenuListFragment():MenuListFragment

    @ContributesAndroidInjector
    abstract fun contributeCheckSelectedDishesFragment(): CheckSelectedDishesFragment

    @ContributesAndroidInjector
    abstract fun contributeListOfSelectedDishes(): ListOfPlacesSelectedDishesFragment

    @ContributesAndroidInjector
    abstract fun contributeListOfPlacesFragment():ListOfPlacesFragment

    @ContributesAndroidInjector
    abstract fun contributeCheckUserMenu():CheckUserMenuFragment

}