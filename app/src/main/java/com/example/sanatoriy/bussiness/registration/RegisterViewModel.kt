package com.example.sanatoriy.bussiness.registration

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myroutekotlin.InternetConnection.Retrofit.Common
import com.example.sanatoriy.InternetConnection.Model.ErrorMessageModel
import com.example.sanatoriy.InternetConnection.Model.registration.RegisterModel
import com.example.sanatoriy.InternetConnection.Model.tableNumberModel
import com.example.sanatoriy.data.user.UserDataRepositoriy
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RegisterViewModel  @Inject constructor(private val UDR: UserDataRepositoriy,val context: Context) {

    private var _tableState = MutableLiveData<String>()
    val tableState: LiveData<String>
        get() = _tableState

    val welcomeText:String
    get() {
        return "Берестье"
    }

    fun setTable(number : String, userId:String) {
        try{
            if (number.toInt() in 1..99) {
//                UDR.setTableNumber(number.toInt())
                UDR.registerUser(userId)
                _tableState.value = "success"
            }
            else{
                Toast.makeText(context, "Данные не корректны", Toast.LENGTH_LONG).show()
            }
        }
        catch (e: Exception){

        }

    }

    fun registerUser(numberTable : Int){
        Common.retrofitService.registerUserTable(tableNumberModel(numberTable)).enqueue(object:
            Callback<RegisterModel> {
            override fun onResponse(
                call: Call<RegisterModel>,
                response: Response<RegisterModel>
            ) {
                if(response.code() == 201){
                    Log.i("Request", "RegisterUserTable Всё ОК")
                    setTable(numberTable.toString(),response.body()?.data?.id.toString())
                }
                else{
                    Log.i("Request", "RegisterUserTable Вызвано исключение")
                    val gson = GsonBuilder().create()
                    var pojo = gson.fromJson(
                        response.errorBody()!!.string(),
                        ErrorMessageModel::class.java)
                        Toast.makeText(context,pojo.errorText.toString(),Toast.LENGTH_LONG).show()

                }
            }
            override fun onFailure(call: Call<RegisterModel>, t: Throwable) {
                Log.i("Request", " RegisterUserTable Server Error")

            }

        })

    }


}