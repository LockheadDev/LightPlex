package com.example.lightplex.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lightplex.R
import com.example.lightplex.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseUser
import com.example.lightplex.utils.FirebaseUtils.firebaseAuth



class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignIn.setOnClickListener {
            val email = binding.etSignInEmail.text.toString().trim()
            val password = binding.etSignInPassword.text.toString().trim()

            if (!(email.isNullOrEmpty() && password.isNullOrEmpty())) {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { respuesta ->
                    if(respuesta.isSuccessful){
                        findNavController().navigate(R.id.action_loginFragment_to_menuFragment)
                    }else{
                        Toast.makeText(activity, "Error de login", Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                Toast.makeText(activity, "email or password incorrect", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnCreateAccount2.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registraUsuarioFragment)
        }
    }


    /*override fun onStart() {
        super.onStart()
        firebaseUser?.let{
            findNavController().navigate(R.id.action_loginFragment_to_listaGastosFragment)
        }
    }*/

    override fun onStart() {
        super.onStart()
        val user : FirebaseUser? = firebaseAuth.currentUser
        user?.let {
            findNavController()
               // .navigate(R.id.action_loginFragment_to_listaGastosFragment)
        }
    }


}