package com.example.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.HourlyForeCast

class HourlyForecastAdapter: RecyclerView.Adapter<HourlyForecastVH>(){

    private val items = arrayListOf<HourlyForeCast>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyForecastVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hourly_forecast,parent,false)
        return HourlyForecastVH(view)
    }

    override fun getItemCount()= items.count()


    override fun onBindViewHolder(holder: HourlyForecastVH, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(newItems: List<HourlyForeCast>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }


}
28  app/src/main/java/kg/tutorialapp/weather/ui/rv/HourlyForecastVH.kt
@@ -0,0 +1,28 @@
package kg.tutorialapp.weather.ui.rv

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kg.tutorialapp.weather.format
import kg.tutorialapp.weather.models.Constants
import kg.tutorialapp.weather.models.HourlyForeCast
import kotlinx.android.synthetic.main.item_hourly_forecast.view.*
import kotlin.math.roundToInt

class HourlyForecastVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: HourlyForeCast){

        itemView.run {
            tv_time.text = item.date.format("HH:mm")
            item.probability?.let {
                tv_precipitation.text = "${(it *100).roundToInt()} %"
            }
            tv_temp.text = item.temp?.roundToInt().toString()
            Glide.with(itemView.context)
                .load("${Constants.iconUri}${item.weather?.get(0)?.icon}${Constants.iconFormat}")
                .into(iv_weather_icon)

        }
    }
}

