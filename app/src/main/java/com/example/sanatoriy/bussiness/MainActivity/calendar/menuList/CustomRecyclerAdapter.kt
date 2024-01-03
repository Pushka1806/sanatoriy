package com.example.sanatoriy.bussiness.MainActivity.calendar.menuList



import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sanatoriy.InternetConnection.Model.Dishes
import com.example.sanatoriy.R


class CustomRecyclerAdapter(private val bludes: List<Dishes>, private val contextAct: Context, private val menuViewModel: MenuViewModel) : RecyclerView
.Adapter<RecyclerView.ViewHolder>() {

    private val  radioButtonPlaceMap = hashMapOf(R.id.bt_radio1 to 0, R.id.bt_radio2 to 1,
        R.id.bt_radio3 to 2, R.id.bt_radio4 to 3 , R.id.bt_radio5 to 4, R.id.bt_radio6 to 5)

    private lateinit var  radioButtonViewMap: HashMap<Int,RadioButton>

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var meal_id: String? = "0"
        val bt_rad1: RadioButton = itemView.findViewById(R.id.bt_radio1)
        val bt_rad2: RadioButton = itemView.findViewById(R.id.bt_radio2)
        val bt_rad3: RadioButton = itemView.findViewById(R.id.bt_radio3)
        val bt_rad4: RadioButton = itemView.findViewById(R.id.bt_radio4)
        val bt_rad5: RadioButton = itemView.findViewById(R.id.bt_radio5)
        val bt_rad6: RadioButton = itemView.findViewById(R.id.bt_radio6)

        val nameMeal: TextView = itemView.findViewById(R.id.name_item)
        val massMeal: TextView = itemView.findViewById(R.id.mass_item)
        val kbju: TextView = itemView.findViewById(R.id.kbju_item)
        val diet1: TextView = itemView.findViewById(R.id.diet1)
        val diet2: TextView = itemView.findViewById(R.id.diet2)
        val diet3: TextView = itemView.findViewById(R.id.diet3)
        val diet4: TextView = itemView.findViewById(R.id.diet4)
        val diet5: TextView = itemView.findViewById(R.id.diet5)
        val diet6: TextView = itemView.findViewById(R.id.diet6)
        val dietsMeal = arrayListOf<TextView>(diet1, diet2, diet3, diet4, diet5, diet6)
        val image: ImageView = itemView.findViewById(R.id.image_item)
//        val kids: TextView = itemView.findViewById(R.id.kids_item)
        //val dejurnoe_bludo: TextView = itemView.findViewById(R.id.dejurnoe_bludo_item)
    }

    class MyButtonHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var nextPage:ImageView = itemView.findViewById(R.id.nextPageButton)
        var previousPage:ImageView = itemView.findViewById(R.id.previousPageButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return when(viewType){
           R.layout.new_menu_item -> {
               val itemView = LayoutInflater.from(parent.context)
                   .inflate(R.layout.new_menu_item, parent, false)
               MyViewHolder(itemView)
           }
           R.layout.button -> {
               val itemView =  LayoutInflater.from(parent.context).inflate(R.layout.button, parent, false)
               MyButtonHolder(itemView)
           }
           else -> {throw IllegalArgumentException("unknown view type $viewType")}
       }

    }

    override fun getItemViewType(position: Int): Int {
        return if (position == bludes.size) R.layout.button else R.layout.new_menu_item
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //Log.i("TAG", "OnBind")
        if (position == bludes.size) {
            val holder = holder as MyButtonHolder
            holder.nextPage.setOnClickListener(View.OnClickListener {
                menuViewModel.nextPage() })
            holder.previousPage.setOnClickListener {
                menuViewModel.previousPage()
            }
        }
        else {
            val holder = holder as MyViewHolder
            radioButtonViewMap = hashMapOf(0 to holder.bt_rad1, 1 to holder.bt_rad2,
                2 to holder.bt_rad3, 3 to holder.bt_rad4, 4 to holder.bt_rad5, 5 to holder.bt_rad6)
            holder.meal_id = bludes[position].id
            holder.nameMeal.text = bludes[position].name
            holder.massMeal.text = bludes[position].weight
            holder.kbju.text = readKbju(holder, position)
            var diets = bludes[position].dieta
            for (index in diets!!.indices) {
                holder.dietsMeal[index].text = diets[index]
            }
            if (bludes[position].image == null) {
                holder.image.setImageResource(R.drawable.image_error)
            } else {
                Glide.with(contextAct)
                    .load("http://31.129.103.95:8111/${bludes[position].image}")
                    .error(R.drawable.image_error)
                    .into(holder.image)
            }
            radioButtonViewMap.forEach{
                val placeNumber = it.key
                val radioButton = it.value
                if(!menuViewModel.disableListofUsers.contains(it.key)){
                    radioButton.isChecked = findDishInUserTable(bludes[position], placeNumber)
                    radioButton.setOnClickListener { p0 -> findItemList(p0) }
                }
                else{
                    radioButton.isEnabled = false
                }
            }
        }
    }


    override fun getItemCount() = bludes.size + 1

    private fun readKbju(holder: MyViewHolder, position: Int): String {
        var kbju_item: String = " "
        kbju_item += bludes[position].calories
//
        return kbju_item
    }

    private fun findItemList(p0: View?) {
        val parent_row = p0!!.parent.parent.parent.parent.parent.parent as View
        val lv: RecyclerView = parent_row.parent as RecyclerView
        val position: Int = lv.getChildLayoutPosition(parent_row)
        filterItem(position, p0.id)

    }

    @SuppressLint("NotifyDataSetChanged", "SuspiciousIndentation")
    fun filterItem(position: Int, radBt: Int) {
        val placeNumber = radioButtonPlaceMap[radBt]
        var dishBoolean = findDishInUserTable(bludes[position], placeNumber!!)
               if(!dishBoolean){
                   menuViewModel.addDish(bludes[position],placeNumber)
               }
               else{
                   menuViewModel.removeDish(bludes[position],placeNumber)
               }
        notifyDataSetChanged()
    }

    private fun findDishInUserTable (dish:Dishes, placeNumber:Int ):Boolean {
        return menuViewModel.findDish(dish,placeNumber)
    }


}