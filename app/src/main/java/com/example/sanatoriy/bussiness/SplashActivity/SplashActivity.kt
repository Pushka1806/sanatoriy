package com.example.sanatoriy.bussiness.SplashActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sanatoriy.MyApplication
import com.example.sanatoriy.R
import com.example.sanatoriy.bussiness.MainActivity.MainViewModel
import com.example.sanatoriy.bussiness.registration.RegisterActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        viewModel.requestGetTypeOfFoodIntake()
        viewModel.requestGetTypeOfDish()

        viewModel.catalog.observe(this){
            if(it == 2){
                startActivity(Intent(this, RegisterActivity::class.java))
            }
        }
    }
}