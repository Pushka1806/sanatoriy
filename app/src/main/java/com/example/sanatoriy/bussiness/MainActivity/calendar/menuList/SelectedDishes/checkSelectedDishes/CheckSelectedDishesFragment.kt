package com.example.sanatoriy.bussiness.MainActivity.infoOfUserMenu.checkMenu


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import com.example.sanatoriy.R
import com.example.sanatoriy.bussiness.MainActivity.calendar.infoOfUserMenu.checkMenu.CustomExpandableListAdapter
import com.example.sanatoriy.bussiness.MainActivity.calendar.menuList.SelectedDishes.ListOfPlacesSelectedDishesViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [CheckSelectedDishesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CheckSelectedDishesFragment : DaggerFragment() {
 lateinit var aplContext:Context
 var placeNumber: Int? = null
    private var expandableListView: ExpandableListView? = null
    private var adapter: ExpandableListAdapter? = null
    private var titleList: List<String>? = null

    @Inject
    lateinit var viewModel: ListOfPlacesSelectedDishesViewModel

    override fun onAttach(context: Context) {
        aplContext = context
        super.onAttach(context)
    }
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            placeNumber= it.getInt("place_number")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            expandableListView = view.findViewById(R.id.expendableList)
            if (expandableListView != null) {
                val listData = viewModel.getSelectedDishesForCheckUserMenu(placeNumber!!)
                titleList = ArrayList(listData.keys)
                adapter = CustomExpandableListAdapter(aplContext, titleList as ArrayList<String>, listData)
                expandableListView!!.setAdapter(adapter)
            }
        }

    }

