package com.example.lightplex.fragments

import android.content.Context
import android.content.Intent
import android.hardware.camera2.CameraManager
import com.example.lightplex.adapter.LightControlsAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lightplex.activities.FlashLightDimmer
import com.example.lightplex.databinding.FragmentMenuBinding
import com.example.lightplex.provider.LightControlsDataProvider

//MUST DO
//TODO Add layout for each control
//TODO DO PRESENTATION

//COOL TO HAVE
//TODO Add app icon
//TODO Disable callback for splash Screen

class MenuFragment : Fragment() {
    lateinit var cameraManager : CameraManager
    lateinit var cameraId : String
    private lateinit var binding : FragmentMenuBinding
    var status: Boolean = false
    private var controles = mutableListOf<String>("onoff","dimmer","timer","morse","sos","custom")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter(controles)
        initGUI()
        cameraManager = activity?.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        cameraId = cameraManager.cameraIdList[0]
    }

    override fun onCreateView(
        iinflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(layoutInflater)
        return binding.root
    }

    fun initGUI()
    {
        //TODO open dialog to choose module number or name...
        //ADD and update modules
        binding.fab.setOnClickListener {
            controles.add("custom")
            initAdapter(controles)
        }
    }

    fun initAdapter(controles : MutableList<String>)
    {
        val recycler : RecyclerView = binding.cardsRecyclerView
        val quotesAdapter = LightControlsAdapter(LightControlsDataProvider.getProperData(controles,this.requireContext()))

        //CHECK ONCLICK ON ELEMENTS
        quotesAdapter.setOnItemClickListener( object : LightControlsAdapter.ClickListener{
            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(context, quotesAdapter.getData(position).function, Toast.LENGTH_SHORT).show()
                //ELEMENT CLICK MANAGER
                when(quotesAdapter.getData(position).function)
                {
                    "timer"-> showTimePickerDialog()
                    "dimmer"-> showDimmerFragment()
                    "sos"-> sosBehaviour()
                    "onoff"-> onoffBehaviour()
                    "morse"-> showMorseFragment()
                    "custom" -> showCustomFragment()
                }
            }
        })

        //TWO COLUMN RECYCLERVIEW LAYOUT
        recycler.layoutManager = GridLayoutManager(context, 2)

        //LOAD DATA INTO LAYOUT
        recycler.adapter = quotesAdapter
    }

    private fun showCustomFragment() {
        val action: NavDirections = MenuFragmentDirections.actionMenuFragmentToCustomFragment()
        findNavController().navigate(action)
        // TODO Do modifiable layout to create new behaviours... (hard)
    }

    //MORSE FUNCS
    private fun showMorseFragment() {
        val action: NavDirections = MenuFragmentDirections.actionMenuFragmentToMorseFragment()
        findNavController().navigate(action)
        // TODO Do layout
    }
    //ON OFF FUNCS
    private fun onoffBehaviour() {
        status = !status
        cameraManager.setTorchMode(cameraId, status)
    }
    //SOS FUNCS
    private fun sosBehaviour() {
    //TODO Create behaviour
    }
    //DIMMER FUNCS
    private fun showDimmerFragment() {
        val intent = Intent(context,FlashLightDimmer::class.java)
        context?.startActivity(intent)

    //val action: NavDirections = MenuFragmentDirections.actionMenuFragmentToDimmerFragment()
    //findNavController().navigate(action)
       // TODO Do layout
    }
    //TIMER FUNCS
    private fun showTimePickerDialog() {
        val timePicker = TimePickerFragment { onTimeSelected(it) }
        timePicker.show(parentFragmentManager, "timePicker")
    }
    private fun onTimeSelected(time: String) {
        Toast.makeText(context, time, Toast.LENGTH_SHORT).show()
    }

}