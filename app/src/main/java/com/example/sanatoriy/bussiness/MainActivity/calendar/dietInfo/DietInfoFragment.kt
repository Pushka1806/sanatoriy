package com.example.sanatoriy.bussiness.MainActivity.calendar.dietInfo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.constraintlayout.helper.widget.Carousel
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.sanatoriy.R


class DietInfoFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var textCenter: TextView
    private lateinit var textUp: TextView
    private lateinit var textFront: TextView
    var list:List<String>?=null;


    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_diet_info, container, false)
       val motionLayout: MotionLayout = view.findViewById(R.id.motion_layout);
        textCenter = motionLayout.findViewById(R.id.text_center);
        textUp =motionLayout.findViewById(R.id.text_up);
        textFront=motionLayout.findViewById(R.id.text_front);
        val carousel: Carousel = motionLayout.findViewById(R.id.carousel);
        carousel.setAdapter(object : Carousel.Adapter {
            override fun count(): Int {
                return 4;
            }

            override fun populate(view: View, index: Int) {
            when(index){
                0 -> {textUp.setText(R.string.diet1)
                          textFront.setText(R.string.diet2)
                             textCenter.setText(R.string.diet3)
                Log.e("E",index.toString())}
                1 -> {textUp.setText(R.string.diet2)
                         textFront.setText(R.string.diet3)
                              textCenter.setText(R.string.diet4)
                    Log.e("E",index.toString())}
                2 -> {textUp.setText(R.string.diet3)
                         textFront.setText(R.string.diet4)
                             textCenter.setText(R.string.diet1)
                    Log.e("E",index.toString())}
                3 -> {textUp.setText(R.string.diet4)
                         textFront.setText(R.string.diet1)
                             textCenter.setText(R.string.diet2)
                    Log.e("E",index.toString())}



            }
            }

            override fun onNewItem(index: Int) {
                // Called when an item is set.
            }});
        // Inflate the layout for this fragment
        return view;
    }


}