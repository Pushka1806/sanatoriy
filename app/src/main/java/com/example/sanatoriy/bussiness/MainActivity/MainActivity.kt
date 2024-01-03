package com.example.sanatoriy.bussiness.MainActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MenuItem.OnMenuItemClickListener
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.sanatoriy.R
import com.example.sanatoriy.bussiness.MainActivity.calendar.CalendarViewModel
import com.example.sanatoriy.bussiness.MainActivity.calendar.calendar_interface
import com.example.sanatoriy.bussiness.registration.RegisterActivity

import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(), calendar_interface
    {
    @Inject
    lateinit var calendarViewModel: MainViewModel

    @Inject
    lateinit var viewModel:CalendarViewModel

    lateinit var toolbar:androidx.appcompat.widget.Toolbar

    @SuppressLint("MissingInflatedId", "SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_of_menu)
        toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.main_menu)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
         navHostFragment.findNavController().run{
            toolbar.setupWithNavController(this,AppBarConfiguration(graph))
        }

        toolbar.setNavigationOnClickListener{
            onBackPressed()
        }


        toolbar.setOnMenuItemClickListener {
            Log.i("TAG", "MenuItem")
            if(it.itemId == R.id.logout_item){
                viewModel.unregister()
                startActivity(Intent(baseContext, RegisterActivity::class.java))
            }
            return@setOnMenuItemClickListener true
        }
        calendarViewModel.initCatalogs()

    }

    override fun setCheckMenuDate(date: String) {
       calendarViewModel.setCheckMenuDate(date)
    }

    override fun setZakazMenuDate(date: String) {
        calendarViewModel.setZakazMenuDate(date)
    }


}