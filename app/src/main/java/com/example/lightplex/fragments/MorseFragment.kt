package com.example.lightplex.fragments

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lightplex.databinding.FragmentMorseBinding

class MorseFragment : Fragment() {
    private lateinit var binding : FragmentMorseBinding
    private var f_alf2morse = true
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        InitBotones()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMorseBinding.inflate(layoutInflater)
        return binding.root
    }
private fun InitBotones(){
    binding.traducirboton?.setOnClickListener {
        if(f_alf2morse == true){

           var texto = binding.alphaText?.text.toString()
           //Toast.makeText(context, "f_alf2morse = true", Toast.LENGTH_SHORT).show()
           binding.morsetext?.text = tomorse(texto)
        }
        else{

            var textomorse = binding.morsetext?.text.toString()
            Toast.makeText(context, "Morse a Alpha: ${toalpha(textomorse)}", Toast.LENGTH_SHORT).show()
        }
    }
    binding.clearboton?.setOnClickListener {
        binding.morsetext?.text = ""
    }
    binding.lineaboton?.setOnClickListener {
        binding.morsetext?.text = binding.morsetext?.text.toString() + "-"
    }
    binding.puntobutton?.setOnClickListener {
        binding.morsetext?.text = binding.morsetext?.text.toString() + "."
    }
    binding.espacioboton?.setOnClickListener {
        binding.morsetext?.text = binding.morsetext?.text.toString() + " "
    }
    binding.nuevapalabraboton?.setOnClickListener {
        binding.morsetext?.text = binding.morsetext?.text.toString() + " / "
    }
    binding.seleccionswitch?.setOnCheckedChangeListener { compoundButton, b ->
        f_alf2morse =! b
    }

}

    private fun toalpha(str: String): String {
        var str2 = str.lowercase().trim()
        var textof = str2.split(" ")
        var sfinal = " "
        for(n in 0..(textof.size-1)){
            sfinal += morse2alf(textof[n])
        }
        return sfinal
    }
    private fun morse2alf(letra:String):String{

        var morse = ""
        if(letra == ".-") {morse = "a"}
        else if (letra == "-..." ){morse  = "b"}
        else if (letra == "-.-." ){morse  = "c"}
        else if (letra == "-.." ){morse  = "d"}
        else if (letra == "." ){morse  = "e"}
        else if (letra == "..-." ){morse  = "f"}
        else if (letra == "--." ){morse  = "g"}
        else if (letra == "...." ){morse  = "h"}
        else if (letra == ".." ){morse  = "i"}
        else if (letra == ".---" ){morse  = "j"}
        else if (letra == "-.-" ){morse  = "k"}
        else if (letra == ".-.." ){morse  = "l"}
        else if (letra == "--" ){morse  = "m"}
        else if (letra == "-." ){morse  = "n"}
        else if (letra == "---" ){morse  = "o"}
        else if (letra == ".--." ){morse  = "p"}
        else if (letra == "--.-" ){morse  = "q"}
        else if (letra == ".-." ){morse  = "r"}
        else if (letra == "..." ){morse  = "s"}
        else if (letra == "-" ){morse  = "t"}
        else if (letra == "..-" ){morse  = "u"}
        else if (letra == "...-" ){morse  = "v"}
        else if (letra == ".--" ){morse  = "w"}
        else if (letra == "-..-" ){morse  = "x"}
        else if (letra == "-.--" ){morse  = "y"}
        else if (letra == "--.." ){morse  = "z"}
        else if (letra == ".----" ){morse  = "1"}
        else if (letra == "..---" ){morse  = "2"}
        else if (letra == "...--" ){morse  = "3"}
        else if (letra == "....-" ){morse  = "4"}
        else if (letra == "-...." ){morse  = "5"}
        else if (letra == "--..." ){morse  = "6"}
        else if (letra == "--..." ){morse  = "7"}
        else if (letra == "---.." ){morse  = "8"}
        else if (letra == "----." ){morse  = "9"}
        else if (letra == "----"   ){morse  = "0"}
        else if (letra == "/"){morse  = " "}
        else{morse = "#"}
        return morse
    }

    private fun tomorse(str: String):String{

        var str2 = str.lowercase().trim()
        var textof = str2.split(" ")
        var sfinal = " "

        for(n in 0..(textof.size-1)){
            for(i in 0..(textof[n].length-1)){
                var palabra = textof[n]
                sfinal += alf2morse(palabra[i])
            }
            sfinal += "/ "
        }


        return sfinal
    }
    private fun alf2morse(letra:Char):String{
        var morse = ""
        when(letra){
            'a' -> morse = ".-"
            'b' -> morse = "-..."
            'c' -> morse = "-.-."
            'd' -> morse = "-.."
            'e' -> morse = "."
            'f' -> morse = "..-."
            'g' -> morse = "--."
            'h' -> morse = "...."
            'i' -> morse = ".."
            'j' -> morse = ".---"
            'k' -> morse = "-.-"
            'l' -> morse = ".-.."
            'm' -> morse = "--"
            'n' -> morse = "-."
            'o' -> morse = "---"
            'p' -> morse = ".--."
            'q' -> morse = "--.-"
            'r' -> morse = ".-."
            's' -> morse = "..."
            't' -> morse = "-"
            'u' -> morse = "..-"
            'v' -> morse = "...-"
            'w' -> morse = ".--"
            'x' -> morse = "-..-"
            'y' -> morse = "-.--"
            'z' -> morse = "--.."
            '1' -> morse = ".----"
            '2' -> morse = "..---"
            '3' -> morse = "...--"
            '4' -> morse = "....-"
            '5' -> morse = "-...."
            '6' -> morse = "--..."
            '7' -> morse = "--..."
            '8' -> morse = "---.."
            '9' -> morse = "----."
            '0' -> morse = "----"
            else -> morse = "#"
        }
        return morse + " "
    }
}