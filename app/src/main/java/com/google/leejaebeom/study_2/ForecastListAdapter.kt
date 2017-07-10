package com.google.leejaebeom.study_2

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.find

/**
 * Created by leejaebeom on 2017. 7. 9..
 */
class ForecastListAdapter(forecast: ForecastResult, context: Context) : RecyclerView.Adapter<ViewHolder>(){
    var items : List<Forecast?>
    lateinit var mInflater : LayoutInflater
    init {
        items = forecast.list
        mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(mInflater.inflate(R.layout.recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindData(items.get(position))
    }

    override fun getItemCount(): Int = items.size

}

class ViewHolder (itemView: View?) : RecyclerView.ViewHolder(itemView) {
    val date = itemView?.find<TextView>(R.id.item_date)
    val am = itemView?.find<TextView>(R.id.item_am)
    val pm = itemView?.find<TextView>(R.id.item_pm)
    val temp = itemView?.find<TextView>(R.id.item_temp)
    init {

    }

    fun bindData(forecast: Forecast?){
        date?.text = "${forecast?.date} 일"
        am?.text = forecast?.amWeather
        pm?.text = forecast?.pmWeather
        temp?.text = "${forecast?.maxTemp} / ${forecast?.minTemp}"
    }
}