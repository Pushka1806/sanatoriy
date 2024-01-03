package com.example.sanatoriy.bussiness.MainActivity.calendar.menuList

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sanatoriy.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MenuListFragment :  DaggerFragment() {

    private lateinit var textTypeOfFood: TextView
    private lateinit var textTypeOfDish: TextView

    private lateinit var recycler: RecyclerView


    private lateinit var mainContext:Context

    @Inject
     lateinit var  globalMenuViewModel: MenuViewModel

    override fun onAttach(context: Context) {
        Log.i("Fragment", "OnAttach")
        mainContext = context
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        if(globalMenuViewModel.getUserTableOfSelectedDishes() == null){
            globalMenuViewModel.createSelectedUserMenuModel()
        }
        globalMenuViewModel.requestGetUserMenu()


        return inflater.inflate(R.layout.menu_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("Fragment", "OnViewCreated")
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)

        globalMenuViewModel.page.observe(viewLifecycleOwner) {
            Log.i("TAG", "observe $it")
            if (it == globalMenuViewModel.maxSizeOfPage) {
                view.findNavController().navigate(R.id.infoOfSelectedDishesFragment)
            } else if (it == 404) {
                Log.i("TAG", "Ошибка меню")
            } else if (it > 0) {
                initTextViews()
                adapterOfCommonMenu(recycler)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                showAlertDialog()
            }

        })

    }

    private fun showAlertDialog() {
        AlertDialog.Builder(requireContext()).setMessage(getString(R.string.alert_message))
            .setPositiveButton(getString(R.string.alert_positive), object: DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                   findNavController().popBackStack(R.id.calendarFragment, true);
                   findNavController().navigate(R.id.calendarFragment)
                }

            })
            .setNegativeButton(getString(R.string.alert_negative), object :DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {

                }

            })
            .show()
    }


    private fun adapterOfCommonMenu(recyclerAdapter: RecyclerView){
        val adapter = CustomRecyclerAdapter(globalMenuViewModel.getDishes(), mainContext, globalMenuViewModel)
        recyclerAdapter.adapter = adapter
    }

    private fun setupViews(view:View){
        textTypeOfFood = view.findViewById(R.id.typeOfFood)
        textTypeOfDish = view.findViewById(R.id.typeOfDish)

        recycler =  view.findViewById(R.id.recyclerViewElement)
        recycler.layoutManager = LinearLayoutManager(mainContext)
    }
    private fun initTextViews(){
        textTypeOfFood.text = globalMenuViewModel.nameTypeOfFood
        textTypeOfDish.text = globalMenuViewModel.nameTypeOfDish
    }
    companion object{
        private const val ISO_8601_24H_FULL_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    }

}

