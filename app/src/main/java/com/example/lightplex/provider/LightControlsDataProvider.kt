package com.example.lightplex.provider

import android.annotation.SuppressLint
import android.content.Context
import com.example.lightplex.R
import com.example.lightplex.data.LightControl

object LightControlsDataProvider {
    @SuppressLint("UseCompatLoadingForDrawables")
    //TODO - Transforms a List of strings into a list of Light controls, needs to be updated for better ID handling!!
    fun getProperData(ListControls : List<String>, context: Context) : List<LightControl>
    {
        var templist : MutableList<LightControl> = mutableListOf()
        for (controltype in ListControls) {
            when (controltype) {
                "onoff" -> templist.add(LightControl(0, context.resources.getDrawable(R.drawable.onoff, context.theme),"onoff",false))
                "dimmer" -> templist.add(LightControl(0, context.resources.getDrawable(R.drawable.dimmer, context.theme),"dimmer",false))
                "timer" -> templist.add(LightControl(0, context.resources.getDrawable(R.drawable.timer, context.theme),"timer",false))
                "morse" -> templist.add(LightControl(0, context.resources.getDrawable(R.drawable.morse, context.theme),"morse",false))
                "sos" -> templist.add(LightControl(0, context.resources.getDrawable(R.drawable.sos, context.theme),"sos",false))
                "custom" -> templist.add(LightControl(0, context.resources.getDrawable(R.drawable.custom, context.theme),"custom",true))
            }
        }
        return templist
    }
}