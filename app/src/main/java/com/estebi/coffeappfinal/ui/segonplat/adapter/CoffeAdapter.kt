package com.estebi.coffeappfinal.ui.segonplat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.estebi.coffeappfinal.R
import com.estebi.coffeappfinal.model.Coffes

class CoffeAdapter (private val  list: List<Coffes>) : RecyclerView.Adapter<CoffeAdapter.CoffeViewHolder>() {
    private lateinit var mListener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(coffe: Coffes)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item_user, parent, false)
        return CoffeViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: CoffeViewHolder, position: Int) {
        val coffe = list[position]
        holder.tvFirstName.text = coffe.coffeName
        holder.tvLastName.text = coffe.coffePrice.toString()
        holder.tvImg.setImageResource(getImg(coffe.coffeName))
    }

    override fun getItemCount(): Int = list.size

    fun getImg(imgName: String): Int {
        if(imgName == "Cafe con leche"){
            return R.drawable.cafeambllet
        }else if(imgName == "Cafe americano"){
            return R.drawable.cafeamericano
        }else if(imgName == "Cafe irlandes"){
            return R.drawable.cafeirlandes
        }else if(imgName == "Cafe espresso"){
            return R.drawable.cafeespresso
        }else if(imgName == "Cafe solo"){
            return R.drawable.cafesolo
        }else if(imgName == "Cafe cortado"){
            return R.drawable.cafecortado
        }else return R.drawable.coffee
    }

    class CoffeViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val tvFirstName : TextView = itemView.findViewById(R.id.tvCoffeName)
        val tvLastName : TextView = itemView.findViewById(R.id.tvCoffePrice)
        val tvImg : ImageView = itemView.findViewById(R.id.imageView2)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(Coffes(tvFirstName.text.toString(), tvLastName.text.toString().toInt()))
            }
        }

    }
}