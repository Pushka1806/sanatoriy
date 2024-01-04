package com.example.sanatoriy.bussiness.MainActivity.calendar.menuList

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myroutekotlin.InternetConnection.Retrofit.Common
import com.example.sanatoriy.InternetConnection.Model.Catalogs.AnswerListofCatalogs
import com.example.sanatoriy.InternetConnection.Model.Dishes
import com.example.sanatoriy.InternetConnection.Model.ErrorMessageModel
import com.example.sanatoriy.InternetConnection.Model.GetUserMenu.GetUserMenuModel
import com.example.sanatoriy.InternetConnection.Model.TableOfSelectedDishes.UserTableOfSelectedDishes
import com.example.sanatoriy.data.user.UserDataRepositoriy
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class  MenuViewModel @Inject constructor(private val UDR: UserDataRepositoriy) {


    private var idTypeOfFood = -2
    private var idTypeOfDish = -2
    var nameTypeOfFood = "None"
    var nameTypeOfDish = "None"
    var disableListofUsers = mutableListOf<Int>()
    var maxSizeOfPage = -1
    private var _page = MutableLiveData<Int>(0)
    val page: LiveData<Int>
        get() {
            return _page
        }

    fun nextPage() {
        if (idTypeOfFood == -1) {
            try {
                idTypeOfFood = 0
                idTypeOfDish = 0
                maxSizeOfPage = initMaxSizeOfPage()
                initTextFront()
//                unableNextPageButton
                _page.value = _page.value?.plus(1)
            } catch (e: java.lang.Exception) {
                _page.value = 404   // обозначает что в меню ошибка
            }
        } else if (idTypeOfDish + 1 in 0 until UDR.globalmenu!!.data.menu.typeOfFoodIntakeItems[idTypeOfFood].typeOfDishItems.size) {
            idTypeOfDish += 1
            initTextFront()
            _page.value = _page.value?.plus(1)
        } else if (idTypeOfFood + 1 in 0 until UDR.globalmenu!!.data.menu.typeOfFoodIntakeItems.size) {
            idTypeOfFood += 1
            idTypeOfDish = 0
            initTextFront()
            _page.value = _page.value?.plus(1)
        } else {
            Log.i("TAG", "last page1")
            _page.value = 999 // остановка выбора и перевод на вывод меню на каждого человека
        }
    }

    fun previousPage(){ // переход на следующий приём пищи или на следующий тип блюда
        if(idTypeOfDish - 1 >= 0){
            idTypeOfDish-=1
            initTextFront()
            _page.value = _page.value?.minus(1)
        }
        else if (idTypeOfFood-1 >= 0){
            idTypeOfFood -=1
            idTypeOfDish = UDR.globalmenu!!.data.menu.typeOfFoodIntakeItems[idTypeOfFood].typeOfDishItems.size-1
            initTextFront()
            _page.value = _page.value?.minus(1)
        }
    }

   fun doubleArrowLeft(){ // переход на предыдущий приём пищи
       if(idTypeOfFood - 1 >= 0){
           val deltaPageValue = idTypeOfDish + UDR.globalmenu!!.data.menu.typeOfFoodIntakeItems[idTypeOfFood-1].typeOfDishItems.size // количество страниц на которое возвращаемся (номер типа блюда плюс размер массива блюд предыдущего приёма пищи)
           idTypeOfFood -= 1
           idTypeOfDish = 0
           initTextFront()
           _page.value = _page.value?.minus(deltaPageValue)
       }
       else{
           Log.i("ERROR", "ABOBA")
       }
   }
    fun doubleArrowRight(){// переход на следующий приём пищи
        if(idTypeOfFood + 1 <= UDR.globalmenu!!.data.menu.typeOfFoodIntakeItems.size-1) {
            val deltaPageValue = UDR.globalmenu!!.data.menu.typeOfFoodIntakeItems[idTypeOfFood].typeOfDishItems.size - idTypeOfDish  // получаем количество страниц на которое нужно перейти вперёд (размер типов блюд минус номер текущего типа)
            idTypeOfFood += 1
            idTypeOfDish = 0
            initTextFront()
            _page.value = _page.value?.plus(deltaPageValue)
        }
        else{
            Log.i("ERROR", "IDFOOD ${idTypeOfFood} SIZE ${UDR.globalmenu!!.data.menu.typeOfFoodIntakeItems.size-1}"  )
        }
    }

    private fun initTextFront() {

        nameTypeOfFood =
            findValueFromCatalog(
                UDR.typeOfFood!!,
                UDR.globalmenu!!.data.menu.typeOfFoodIntakeItems[idTypeOfFood].typeOfFoodIntakeItemId.toString()
            )
                .toString()
        nameTypeOfDish =
            findValueFromCatalog(
                UDR.typeOfDish!!,
                UDR.globalmenu!!.data.menu.typeOfFoodIntakeItems[idTypeOfFood].typeOfDishItems[idTypeOfDish].typeOfDishItemId.toString()
            )
                .toString()
    }


    fun requestGetUserMenu() {
        val tomorrow = LocalDate.now()
            .plusDays(1)//функция возвращает дату а нам нужно на день вперёд       //  val tomorrow = LocalDate.now()
        val dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ssX");
        val formattedTomorrow = tomorrow.atStartOfDay().atOffset(ZoneOffset.UTC).format(dtf);
        Log.i("TIME", formattedTomorrow)
        Common.retrofitService.getUsersMenu(UDR.userId, formattedTomorrow).enqueue(object :
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
                    startPrintMenu()
                    nextPage()
                } else if (response.code() == 500) {
                    Log.i("Response", "GETUSERMENU ERROR")
                    val gson = GsonBuilder().create()
                    val pojo = gson.fromJson(
                        response.errorBody()!!.string(),
                        ErrorMessageModel::class.java
                    )
                    Log.i("Response", pojo.errorText.toString())
                    startPrintMenu()
                    nextPage()
                }
            }

            override fun onFailure(call: Call<GetUserMenuModel>, t: Throwable) {
                Log.i("Server", "GETUSERMENU SERVER ERROR")
            }

        })
    }

    private fun findValueFromCatalog(catalog: AnswerListofCatalogs, value: String): String? {
        return UDR.findValueFromCatalog(catalog, value)
    }

    fun getDishes(): List<Dishes> {
        return UDR.getDishes(idTypeOfFood, idTypeOfDish)
    }

    fun startPrintMenu() {
        idTypeOfFood = -1
        idTypeOfDish = -1
    }
    private fun initMaxSizeOfPage() : Int{  //получили максимальное количество комбинаций типов приёмов пищи и блюд
        var maxPage = 0;
        UDR.globalmenu!!.data.menu.typeOfFoodIntakeItems.forEach {maxPage += 1 * it.typeOfDishItems.size  }
        return maxPage
    }

    @SuppressLint("SuspiciousIndentation")
    fun findDish(dish: Dishes, placeNumber: Int): Boolean {
        val userMenu  = UDR.getUserMenuOfPlaceNumber(placeNumber)
        val tfood = userMenu.selectedMenuOfPlace[dish.typeOfFoodIntakeItemId]
        val bludo  = tfood!![dish.typeOfDishId]
        return !(bludo == null || bludo.id != dish.id)
    }


    fun addDish(bludo: Dishes, placeNumber: Int) {
        Log.i("filter_item", "addBludo")
        val typeFood =
            UDR.userTableOfSelectedDishes!!.itemsOfTable[placeNumber].selectedMenuOfPlace[bludo.typeOfFoodIntakeItemId]
        typeFood!![bludo.typeOfDishId!!] = bludo

    }

    fun removeDish(bludo: Dishes, placeNumber: Int) {
        Log.i("filter_item", "removeBludo")
        val typeFood =
            UDR.userTableOfSelectedDishes!!.itemsOfTable[placeNumber].selectedMenuOfPlace[bludo.typeOfFoodIntakeItemId]
        typeFood!![bludo.typeOfDishId!!] = null
    }

    fun createSelectedUserMenuModel(){
        UDR.createSelectedUserMenuModel()
    }

    fun getUserTableOfSelectedDishes():UserTableOfSelectedDishes?{
        return UDR.userTableOfSelectedDishes
    }
}



