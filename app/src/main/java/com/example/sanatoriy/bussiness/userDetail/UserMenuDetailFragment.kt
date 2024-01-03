package com.example.sanatoriy.bussiness.userDetail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sanatoriy.InternetConnection.Model.Dishes
import com.example.sanatoriy.R

import com.example.sanatoriy.bussiness.MainActivity.calendar.menuList.SelectedDishes.ListOfPlacesSelectedDishesViewModel
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [UserMenuDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class   UserMenuDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var place_number: Int? = null
    private var dishes: List<Dishes>? = null
    private var fragmentContext: Context? = null

    @Inject
    lateinit var usersViewModel: ListOfPlacesSelectedDishesViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

//        (activity as TableOfUsersActivity).usersComponent.inject(this)
        fragmentContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            place_number= it.getInt("table_place_number")
            Log.i("TAG","place_number ${place_number}")
           // dishes = usersViewModel.getUserDishes(place_number!!)
            Log.i("TAG","dishes size ${dishes!!.size}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_menu_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var backButton = view.findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener(View.OnClickListener {
            //sfi.backFragment()
        })
//        view.findViewById<Button>(R.id.change_button).setOnClickListener(View.OnClickListener {
//            var arguments = Bundle()
//            arguments.putString("")
//            startActivity()
//        })
        var recycler = view.findViewById<RecyclerView>(R.id.userDetailList)
        recycler.layoutManager = LinearLayoutManager(fragmentContext)
        var adapter = UserDetailRecyclerAdapter(dishes!!)
        recycler.adapter = adapter
        super.onViewCreated(view, savedInstanceState)
    }


}