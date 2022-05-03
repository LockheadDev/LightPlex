package com.example.lightplex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lightplex.R
import com.example.lightplex.data.CustomData
import com.example.lightplex.data.MorseData

class CustomDataAdapter ( private val lista: List<CustomData>) :
    RecyclerView.Adapter<CustomDataAdapter.CustomDataViewHolder>(){

    private var clickListener: ClickListener? = null

    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }
    interface ClickListener{
        fun onItemClick(view: View, position: Int)
    }

    inner class CustomDataViewHolder(view: View) :
        RecyclerView.ViewHolder(view), View.OnClickListener{
        val textview : TextView = view.findViewById(R.id.customTextView)
        fun bind(customData : CustomData){
            textview.text = customData.id.toString()
        }
        init {
            view.setOnClickListener(this)
        }
        override fun onClick(view: View?) {
            if(view != null) {
                clickListener?.onItemClick(view, bindingAdapterPosition)
            }
        }

    }
    // Construye el renglon PERO no le da datos a los elementos gráficos
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_layout, parent, false)
        return CustomDataViewHolder(view)
    }

    override fun getItemCount() = lista.size

    fun getData( position: Int ): CustomData {
        return lista[position]
    }

    override fun onBindViewHolder(holder: CustomDataAdapter.CustomDataViewHolder, position: Int) {
        val quote = lista[position]
        holder.bind(quote)
    }


}