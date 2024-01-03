package com.example.sanatoriy.bussiness.registration

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.gesture.GestureOverlayView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserManager
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.sanatoriy.MyApplication
import com.example.sanatoriy.R
import com.example.sanatoriy.bussiness.MainActivity.MainActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.android.support.DaggerAppCompatActivity
import java.lang.Math.abs
import javax.inject.Inject
private const val SUCCESS_VALUE = "success"

class RegisterActivity  : DaggerAppCompatActivity() {
    private lateinit var btNumberTable: Button

    @Inject
    lateinit var registerViewModel: RegisterViewModel

    @Inject
    lateinit var userManager: com.example.sanatoriy.data.user.UserManager

    var currentPageNumber = 0
    var lastValue = 0.0f
    lateinit var  viewPager:ViewPager2

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val userManager = (application as MyApplication).appComponent.userManager()
        if (!userManager.isUserLoggedIn()) {
            Log.i("TAG", "isUserLoggedIn")
            userManager.userJustLoggedIn()
            if (userManager.isUserRegistered()) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
//        userManager.userComponent!!.inject(this)
        setContentView(R.layout.register2)
        setupViews()
        registerViewModel.tableState.observe(this, Observer<String> { state ->
            when (state) {
                SUCCESS_VALUE -> {
                    startActivity(
                        Intent(this, MainActivity::class.java)
                    )
                    finish()
                }
            }
        })
        viewPager = findViewById<ViewPager2>(R.id.view_pager)
        val touchZone = findViewById<GestureOverlayView>(R.id.touchOverlay)
        viewPager.isUserInputEnabled = false //убираем скролл у viewPager
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((40 * Resources.getSystem().displayMetrics.density).toInt()))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.80f + r * 0.20f)
        }
        viewPager.setPageTransformer(compositePageTransformer)


        val demoData = mutableListOf<Int>()
        for (i in 1..100) {
            demoData.add(i)
        }

        viewPager.adapter = CarouselRVAdapter(demoData)

        viewPager.apply {
            clipChildren = false  // No clipping the left and right items
            clipToPadding = false  // Show the viewpager in full width without clipping the padding
            offscreenPageLimit = 3  // Render the left and right items
            (getChildAt(0) as RecyclerView).overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER // Remove the scroll effect
        }
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPageNumber = position
            }

        })
        touchZone.addOnGestureListener(object :
            GestureOverlayView.OnGestureListener {
            override fun onGestureStarted(overlay: GestureOverlayView?, event: MotionEvent?) {
                handleOnTouchEvent(event)
                Log.i("Touch", "start")
            }

            override fun onGesture(overlay: GestureOverlayView?, event: MotionEvent?) {
                handleOnTouchEvent(event)
                Log.i("Touch", "onGesture")
            }

            override fun onGestureEnded(overlay: GestureOverlayView?, event: MotionEvent?) {
                handleOnTouchEvent(event)
                Log.i("Touch", "Ended")
            }

            override fun onGestureCancelled(overlay: GestureOverlayView?, event: MotionEvent?) {
                handleOnTouchEvent(event)
                Log.i("Touch", "cancelled")
            }

        })
        findViewById<TextView>(R.id.bt_send_number_table).setOnClickListener {
            Log.i("TAG", demoData[currentPageNumber].toString())
              registerViewModel.registerUser(demoData[currentPageNumber])
        }
    }

    fun handleOnTouchEvent(event: MotionEvent?): Boolean {

        //Log.i("TAG", event.toString())

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                lastValue = event.x
                if (!viewPager.isFakeDragging) {
                    viewPager.beginFakeDrag()
                }
            }

            MotionEvent.ACTION_MOVE -> {
                val value = event.x
                val delta = value - lastValue
                if (delta > 0) {
                    //Log.i("TAG", "delta left")
                    viewPager.fakeDragBy(delta * 10)
                    lastValue = value
                    return false
                } else {
                   // Log.i("TAG", "right")
                    viewPager.fakeDragBy(delta * 3)
                    lastValue = value
                    return true
                }
            }

            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                viewPager.endFakeDrag()
            }
        }
        return true
    }


    @SuppressLint("SetTextI18n")
    fun setupViews() {
        var bt = findViewById<TextView>(R.id.hello_text)
        bt.text = bt.text.toString() + registerViewModel.welcomeText

    }
}
