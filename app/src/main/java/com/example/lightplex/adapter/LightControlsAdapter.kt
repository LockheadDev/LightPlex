package com.example.lightplex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lightplex.R
import com.example.lightplex.data.LightControl

class LightControlsAdapter ( private val lista: List<LightControl>) :
    RecyclerView.Adapter<LightControlsAdapter.LightControlViewHolder>(){

    private var clickListener: ClickListener? = null

    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }
    interface ClickListener{
        fun onItemClick(view: View, position: Int)
    }

    inner class LightControlViewHolder(view: View) :
        RecyclerView.ViewHolder(view), View.OnClickListener{
        val image : ImageView= view.findViewById(R.id.controlImageView)
        val textview : TextView = view.findViewById(R.id.textviewid)
        fun bind(lightControl : LightControl){
            image.setImageDrawable(lightControl.drawable)
            //We show id on card when the card is custom
            if(lightControl.showId){
                textview.text = lightControl.id.toString()
            }else{
                textview.text = ""
            }

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

    // Construye el renglon PERO no le da datos a los elmentos gráficos
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LightControlViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.control_layout, parent, false)
        return LightControlViewHolder(view)
    }
    override fun onBindViewHolder(holder: LightControlViewHolder, position: Int) {
        val quote = lista[position]
        holder.bind(quote)
    }
    override fun getItemCount() = lista.size

    fun getData( position: Int ): LightControl{
        return lista[position]
    }

}









