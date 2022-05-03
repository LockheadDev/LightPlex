package com.example.lightplex.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lightplex.adapter.CustomDataAdapter
import com.example.lightplex.provider.CustomDataProvider
import com.example.lightplex.databinding.FragmentCustomBinding


class CustomFragment : Fragment() {
    private lateinit var binding : FragmentCustomBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CustomDataProvider.fillData()
        initGUI()
        initAdapter()
    }

    override fun onCreateView(
        iinflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCustomBinding.inflate(layoutInflater)
        return binding.root
    }

    fun initGUI()
    {
        binding.customfab.setOnClickListener {
            CustomDataProvider.addData()
            initAdapter() //Reload all data
        }
    }

    fun initAdapter()
    {
        val recycler : RecyclerView = binding.recyclerview
        val quotesAdapter = CustomDataAdapter(CustomDataProvider.getData())

        //CHECK ONCLICK ON ELEMENTS
        quotesAdapter.setOnItemClickListener( object : CustomDataAdapter.ClickListener{
            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(context, quotesAdapter.getData(position).toString(), Toast.LENGTH_SHORT).show()
            }
        })

        //ONE COLUMN RECYCLERVIEW LAYOUT
        recycler.layoutManager = GridLayoutManager(context, 1)

        //LOAD DATA INTO LAYOUT
        recycler.adapter = quotesAdapter
    }
}