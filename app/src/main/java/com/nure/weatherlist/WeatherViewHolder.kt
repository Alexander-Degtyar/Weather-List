package com.nure.weatherlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_message.view.*

class WeatherAdapter() : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    private val messageList: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message, null)
        return WeatherViewHolder(view)
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.setMessage(messageList.getOrNull(position).orEmpty())
    }

    fun add(data: String) {
        messageList.add(data)
        notifyDataSetChanged()
    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setMessage(s: String) {
            itemView.text.text = s
        }
    }

}