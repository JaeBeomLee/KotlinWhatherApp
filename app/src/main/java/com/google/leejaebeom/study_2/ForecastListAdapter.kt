package com.google.leejaebeom.study_2

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by leejaebeom on 2017. 7. 9..
 */
class ForecastListAdapter(val items : List<String>) : RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent?.context))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.textView?.text = items[position]
    }

    override fun getItemCount(): Int = items.size

}

class ViewHolder(val textView : TextView) : RecyclerView.ViewHolder(textView)