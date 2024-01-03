package com.example.sanatoriy.bussiness.MainActivity.calendar.infoOfUserMenu

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sanatoriy.R
import com.example.sanatoriy.bussiness.MainActivity.MainViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [ListOfPlacesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListOfPlacesFragment : DaggerFragment() {

    @Inject
    lateinit var calendarViewModel: MainViewModel


    lateinit var aplcontext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        aplcontext = context
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_of_places, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarViewModel.setTypeOfFoodCatalog()
        //Log.i("List",calendarViewModel.getListOfPlaceNumbers().toString())
        val adapter = ListOfPlacesAdapter(calendarViewModel.getListOfPlaceNumbers(), calendarViewModel.getEnablePlacesForShow() )
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerViewElement)
        recycler.layoutManager = LinearLayoutManager(aplcontext);
        recycler.adapter = adapter
    }
}