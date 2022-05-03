package com.example.lightplex.provider

import com.example.lightplex.data.CustomData
import com.example.lightplex.data.MorseData

object CustomDataProvider {
private var list : MutableList<CustomData> = mutableListOf()
    private var index : Int = 6
    fun fillData()

    {
        list =  mutableListOf(
            CustomData(1),
            CustomData(2),
            CustomData(3),
            CustomData(4),
            CustomData(5),
        )

    }
    fun getData(): List<CustomData>
    {
        return list
    }

    fun addData()
    {
        list.add(CustomData(index))
        index++
    }
}