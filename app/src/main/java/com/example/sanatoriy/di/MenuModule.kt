package com.example.sanatoriy.di

import com.example.sanatoriy.data.user.MenuSetting

import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class MenuModule {
    @Singleton
    @Provides
    fun provideMenuSettings(): MenuSetting
    {
        return MenuSetting()
    }

}