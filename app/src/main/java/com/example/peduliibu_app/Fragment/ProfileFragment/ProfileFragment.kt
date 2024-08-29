package com.example.peduliibu_app.Fragment.ProfileFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.peduliibu_app.Authenticate.Login.LoginActivity
import com.example.peduliibu_app.Database.UserDatabase
import com.example.peduliibu_app.R
import com.example.peduliibu_app.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    lateinit var auth: FirebaseAuth
    private val userDatabase = UserDatabase()
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            userDatabase.readUsername(auth.currentUser?.uid.toString(), binding.edtName)
            binding.edtEmail.text = auth.currentUser?.email
        }
        binding.btnLogout.setOnClickListener() {
            btnLogout()
        }
    }

    private fun btnLogout() {
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}