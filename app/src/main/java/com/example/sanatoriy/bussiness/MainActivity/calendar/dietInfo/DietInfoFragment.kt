package com.example.sanatoriy.bussiness.MainActivity.calendar.dietInfo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.helper.widget.Carousel
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.sanatoriy.R


class DietInfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    val carousel : Carousel? = null;
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
        val motionLayout: MotionLayout = view.findViewById(R.id.motionLayout);
        val carousel: Carousel = motionLayout.findViewById(R.id.carousel);
        carousel.setAdapter(object : Carousel.Adapter {
            override fun count(): Int {
                return 4;
            }

            override fun populate(view: View, index: Int) {
                // Implement this to populate the view at the given index.
            }

            override fun onNewItem(index: Int) {
                // Called when an item is set.
            }
        })
        // Inflate the layout for this fragment
        return view;
    }


}