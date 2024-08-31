package com.example.peduliibu_app.Fragment.ScheduleFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.peduliibu_app.R
import com.example.peduliibu_app.databinding.FragmentAddEventBinding

class AddEventFragment : Fragment() {
    class AddEventFragment : Fragment() {

        private var _binding: FragmentAddEventBinding? = null
        private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            _binding = FragmentAddEventBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            binding.btnCreateEvent.setOnClickListener {
                val eventName = binding.etEventName.text.toString()
                val eventDescription = binding.etEventDescription.text.toString()

                // Save event to database or storage
                // Navigate back after saving the event
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }
}