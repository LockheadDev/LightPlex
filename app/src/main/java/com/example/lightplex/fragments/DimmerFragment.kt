package com.example.lightplex.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.example.lightplex.R
import com.example.lightplex.databinding.FragmentDimmerBinding
import com.example.lightplex.databinding.FragmentMorseBinding

class DimmerFragment : Fragment(), SeekBar.OnSeekBarChangeListener {
    private lateinit var binding : FragmentDimmerBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGUI()
    }

    override fun onCreateView(
        iinflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDimmerBinding.inflate(layoutInflater)
        return binding.root
    }

    fun initGUI()
    {
        binding.seekBar2.setOnSeekBarChangeListener(this)
    }

    //SEEK BAR FUNCTIONS
    //TODO DIMMER BEHAVIOUR
    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        //TODO("Not yet implemented")
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
        //TODO("Not yet implemented")
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
        //TODO("Not yet implemented")
    }

}