package com.example.sanatoriy.bussiness.MainActivity

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myroutekotlin.InternetConnection.Retrofit.Common
import com.example.sanatoriy.InternetConnection.Model.Catalogs.AnswerListofCatalogs
import com.example.sanatoriy.InternetConnection.Model.CommonMenuModel
import com.example.sanatoriy.InternetConnection.Model.Dishes
import com.example.sanatoriy.InternetConnection.Model.ErrorMessageModel
import com.example.sanatoriy.InternetConnection.Model.GetUserMenu.GetUserMenuModel
import com.example.sanatoriy.InternetConnection.Model.GetUserMenu.UserData
import com.example.sanatoriy.data.user.UserDataRepositoriy
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class MainViewModel @Inject constructor(val context: Context, private val UDR: UserDataRepositoriy ) {

    private var _menu = MutableLiveData<Boolean>(false)
    val menu: LiveData<Boolean>
        get() {
            return _menu
        }



    private var _userMenu = MutableLiveData<Boolean>(false)
    val userMenu: LiveData<Boolean>
        get(){
            return _userMenu
        }

    private var _catalog = MutableLiveData<Int>(0)
    val catalog: LiveData<Int>
        get(){
            return _catalog
        }

    var disableListofUsers = mutableListOf<Int>()

    fun getUserId(): String {
        return UDR.userId
    }

    fun getGlobalMenu():CommonMenuModel?{
        return UDR.globalmenu
    }

    fun setZakazMenuDate(date: String) {
        UDR.setZakazMenuDAte(date)
    }

    fun setCheckMenuDate(date: String) {
        UDR.setCheckMenuDate(date)
    }

    fun sendToast(text:String){
        Toast.makeText(context,text,Toast.LENGTH_LONG ).show()
    }

    fun getListOfPlaceNumbers():List<Int>{
        return UDR.getListOfPlaceNumbers()
    }
    fun setUserMenuFromResponse(data: List<UserData>){
        UDR.setUserMenuFromResponse(data)}

    fun getDishesForCheckUserMenu(placeNumber:Int):HashMap<String,List<Dishes>>{
        return UDR.getHashMapDishesOfPlaceNumber(placeNumber)
    }

    fun getEnablePlacesForShow() : List<Int> {
        return UDR.getEnablePlacesForShow()
    }

    fun initCatalogs() {
        requestGetTypeOfFoodIntake()
        requestGetTypeOfDish()
    }

    private fun getZakazDate(): String {
        return UDR.getZakazMenuDate()
    }

    fun responseGetUserMenu() {
        Log.i("GetUserMenu", "Дата для просмотра пользовательского меню ${UDR.getCheckMenuDate()}")
        Common.retrofitService.getUsersMenu(getUserId(), UDR.getCheckMenuDate()).enqueue(object :
            Callback<GetUserMenuModel> {
            override fun onResponse(
                call: Call<GetUserMenuModel>,
                response: Response<GetUserMenuModel>
            ) {
                if (response.code() == 200) { // блокировка уже выбранных пользователей
                    //placeNumber возврает места от 1, поэтому отнимаем 1 чтобы массив начинался с 0
                    response.body()!!.data.forEach() {
                        disableListofUsers.add(it.placeNumber.toString().toInt() - 1)
                    }
                    setUserMenuFromResponse(response.body()!!.data)
                    _userMenu.value = true


                } else if (response.code() == 500) {
                    Log.i("Response", "GETUSERMENU ERROR")
                    sendToast("Заказы не найдены")
                    val gson = GsonBuilder().create()
                    var pojo = gson.fromJson(
                        response.errorBody()!!.string(),
                        ErrorMessageModel::class.java
                    )
                    Log.i("Response", pojo.errorText.toString())

                }
            }

            override fun onFailure(call: Call<GetUserMenuModel>, t: Throwable) {
                Log.i("Server", "GETUSERMENU SERVER ERROR")
            }

        })
    }
    fun setTypeOfFoodCatalog() {
        Common.retrofitService.getCatalogTypeOfFoodIntake().enqueue(object :
            Callback<AnswerListofCatalogs> {
            override fun onResponse(
                call: Call<AnswerListofCatalogs>,
                response: Response<AnswerListofCatalogs>
            ) {
                if (response.code() == 200) {
                    UDR.setTypeOfFood(response.body()!!)
//                    Log.i("CATALOG", UDR.typeOfFood.toString())

                } else if (response.code() == 401) {
                    Log.i("Response", "GetCatalogTypeOFoodIntake ERROR")
                    val gson = GsonBuilder().create()
                    var pojo = gson.fromJson(
                        response.errorBody()!!.string(),
                        ErrorMessageModel::class.java
                    )
                    Log.i("Response", pojo.errorText.toString())
                }
            }

            override fun onFailure(call: Call<AnswerListofCatalogs>, t: Throwable) {
            }

        })
    }
    fun requestGetTypeOfDish() {
        Common.retrofitService.getCatalogTypeOfDish().enqueue(object :
            Callback<AnswerListofCatalogs> {
            override fun onResponse(
                call: Call<AnswerListofCatalogs>,
                response: Response<AnswerListofCatalogs>
            ) {
                if (response.code() == 200) {
                    UDR.setTypeOfDish(response.body()!!)
                    var k = _catalog.value!!
                    k += 1
                    _catalog.value = k
//                    Log.i("Value", _catalog.value.toString())
                } else if (response.code() == 401) {
                    Log.i("Response", "GetCatalogTypeOfDish ERROR")
                    val gson = GsonBuilder().create()
                    val pojo = gson.fromJson(
                        response.errorBody()!!.string(),
                        ErrorMessageModel::class.java
                    )
                    Log.i("Response", pojo.errorText.toString())
                }
            }

            override fun onFailure(call: Call<AnswerListofCatalogs>, t: Throwable) {

            }
        })
    }
     fun requestGetTypeOfFoodIntake() {
        Log.i("CATALOG", UDR.typeOfFood.toString())
        Common.retrofitService.getCatalogTypeOfFoodIntake().enqueue(object :
            Callback<AnswerListofCatalogs> {
            override fun onResponse(
                call: Call<AnswerListofCatalogs>,
                response: Response<AnswerListofCatalogs>
            ) {
                if (response.code() == 200) {
                    UDR.setTypeOfFood(response.body()!!)
                    var k = _catalog.value!!
                    k += 1
                    _catalog.value = k
//                    Log.i("Value", _catalog.value.toString())
//                    Log.i("CATALOG", UDR.typeOfFood.toString())
                } else if (response.code() == 401) {
                    Log.i("Response", "GetCatalogTypeOFoodIntake ERROR")
                    val gson = GsonBuilder().create()
                    val pojo = gson.fromJson(
                        response.errorBody()!!.string(),
                        ErrorMessageModel::class.java
                    )
                    Log.i("Response", pojo.errorText.toString())
                }
            }

            override fun onFailure(call: Call<AnswerListofCatalogs>, t: Throwable) {

            }

        })
    }


    fun requestGetGlobalMenu() {
        Common.retrofitService.getMenu(getZakazDate())?.enqueue(object : Callback<CommonMenuModel> {
            override fun onResponse(
                call: Call<CommonMenuModel>,
                response: Response<CommonMenuModel>
            ) {
                if (response.code() == 200) {
                    Log.i("Response", "GETMENU OK")
                    UDR.setGlobalMenu(response.body())
                    _menu.value = true


                } else if (response.code() == 401) {
                    Log.i("Response", "GETMENU ERROR")
                    val gson = GsonBuilder().create()
                    val pojo = gson.fromJson(
                        response.errorBody()!!.string(),
                        ErrorMessageModel::class.java
                    )
                    Log.i("Response", pojo.errorText.toString())
                }
            }

            override fun onFailure(call: Call<CommonMenuModel>, t: Throwable) {
                Log.i("TAG", " Server Error GetMenu")
            }

        })
    }











}