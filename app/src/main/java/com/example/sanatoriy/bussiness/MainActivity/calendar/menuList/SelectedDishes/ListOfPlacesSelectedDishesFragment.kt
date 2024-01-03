package com.example.sanatoriy.bussiness.MainActivity.calendar.menuList.SelectedDishes

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myroutekotlin.InternetConnection.Retrofit.Common
import com.example.sanatoriy.InternetConnection.Model.ErrorMessageModel
import com.example.sanatoriy.InternetConnection.Model.registration.RegisterModel
import com.example.sanatoriy.R
import com.google.gson.GsonBuilder
import dagger.android.support.DaggerFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListOfPlacesSelectedDishesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListOfPlacesSelectedDishesFragment : DaggerFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var fragmentContext : Context


    @Inject
    lateinit var viewModel: ListOfPlacesSelectedDishesViewModel


    override fun onAttach(context: Context) {

        fragmentContext = context
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.convertMenuForSend()
        return inflater.inflate(R.layout.fragment_list_of_enter_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = ListOfEnterMenuAdapter(viewModel.getListOfPlaceNumbers(),viewModel.enablePlaces )
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerViewElement)
        recycler.layoutManager = LinearLayoutManager(fragmentContext);
        recycler.adapter = adapter

        view. findViewById<Button>(R.id.send_menu_button).setOnClickListener(View.OnClickListener {

//            Log.i("ForSend", userMenuForSend.toString())
//            Log.i("TAG",userMenuForSend.toString())
            Common.retrofitService.createUserMenu(viewModel.getUserId(),viewModel.sendModel!!).enqueue(object:
                Callback<RegisterModel> {
                override fun onResponse(
                    call: Call<RegisterModel>,
                    response: Response<RegisterModel>
                ) {
                    if (response.code() == 500){
                        Log.i("Response", "createUserMenu ERROR")
                        val gson = GsonBuilder().create()
                        var pojo = gson.fromJson(
                            response.errorBody()!!.string(),
                            ErrorMessageModel::class.java)
                        Toast.makeText(fragmentContext,pojo.errorText.toString(),Toast.LENGTH_LONG).show()
                        Log.i("Response" ,pojo.errorText.toString())
                    }
                    else{
                        Log.i("TAG","CreateUserMenu OK")
                        view.findNavController().navigate(R.id.calendarFragment)
                    }
                }
                override fun onFailure(call: Call<RegisterModel>, t: Throwable) {
                    Log.i("TAG","CreateUserMenu  Error")
                }
            })
        })
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListOfPlacesSelectedDishesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}