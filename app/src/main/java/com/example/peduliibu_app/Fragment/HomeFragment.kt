package com.example.peduliibu_app.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.peduliibu_app.R



class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val button5 = view.findViewById<Button>(R.id.button5)
        button5.setOnClickListener {
            Log.d("HomeFragment", "Navigating to CleaningScheduleFragment")
            findNavController().navigate(R.id.action_homeFragment_to_cleaningScheduleFragment)
            findNavController().popBackStack()
            findNavController().navigate(R.id.homeFragment)
        }

        return view
    }
}