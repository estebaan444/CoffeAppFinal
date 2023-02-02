package com.estebi.coffeappfinal.ui.primerplat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.estebi.coffeappfinal.R
import com.estebi.coffeappfinal.model.Coffes
import com.estebi.coffeappfinal.model.History
import com.estebi.coffeappfinal.model.Pastes

class PastesAdapter(private val list: List<Pastes>) : RecyclerView.Adapter<PastesAdapter.PastesViewHolder>() {
    private lateinit var mListener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(pastes: Pastes)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PastesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item_user, parent, false)
        return PastesViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: PastesAdapter.PastesViewHolder, position: Int) {
        val pasta = list[position]
        holder.tvFirstName.text = pasta.pastaName
        holder.tvLastName.text = pasta.pastaPrice.toString()
        holder.tvImg.setImageResource(getImg(pasta.pastaName))    }

    override fun getItemCount(): Int = list.size


    fun getImg(imgName: String): Int {
        if(imgName == "Tarta de queso"){
            return R.drawable.tartadequeso
        }else if(imgName == "Tarta de chocolate"){
            return R.drawable.tartadechocolate
        }else if(imgName == "Tarta de zanahoria"){
            return R.drawable.tartadezanahoria
        }else if(imgName == "Tarta de manzana"){
            return R.drawable.tartademanzana
        }else if(imgName == "Croisant"){
            return R.drawable.croisanr
        }else if(imgName == "Donut"){
            return R.drawable.donut
        }else return R.drawable.coffee
    }

    class PastesViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val tvFirstName : TextView = itemView.findViewById(R.id.tvCoffeName)
        val tvLastName : TextView = itemView.findViewById(R.id.tvCoffePrice)
        val tvImg : ImageView = itemView.findViewById(R.id.imageView2)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(Pastes(tvFirstName.text.toString(), tvLastName.text.toString().toInt()))
            }
        }
    }


}