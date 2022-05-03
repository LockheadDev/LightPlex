package com.example.lightplex.provider

import com.example.lightplex.data.MorseData

object MorseDataProvider {
    private var list : MutableList<MorseData> = mutableListOf()
    private var index : Int = 6
    fun fillData()
    {
        list =  mutableListOf(
            MorseData(1),
            MorseData(2),
            MorseData(3),
            MorseData(4),
            MorseData(5),
        )

    }
    fun getData(): List<MorseData>
    {
        return list
    }

    fun addData()
    {
        list.add(MorseData(index))
        index++
    }
}