package com.estebi.coffeappfinal.ui.historial.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.estebi.coffeappfinal.R
import com.estebi.coffeappfinal.SharedViewModel.SharedViewModel.Companion.userName
import com.estebi.coffeappfinal.model.History

class HistoryAdapter (private val  list: List<History>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private lateinit var mListener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(history: History)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item_history, parent, false)
        return HistoryViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: HistoryAdapter.HistoryViewHolder, position: Int) {
        val history = list[position]
        holder.tvDate.text = history.date
        holder.tvTotalPrice.text = history.totalPrice.toString()
    }

    override fun getItemCount(): Int = list.size


    class HistoryViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val tvDate : TextView = itemView.findViewById(R.id.date)
        val tvTotalPrice : TextView = itemView.findViewById(R.id.tvTotalPrice)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(History(userName,tvDate.text.toString(), tvTotalPrice.text.toString().toInt()))
            }
        }

    }


}