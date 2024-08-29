package com.example.peduliibu_app.Fragment.NutritionFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.peduliibu_app.Fragment.NutritionFragment.NutritionFragment.NutritionFragment
import com.example.peduliibu_app.R
import com.example.peduliibu_app.databinding.FragmentOpeningNutritionBinding
import androidx.navigation.fragment.findNavController

class OpeningNutritionFragment : Fragment() {

    private var _binding: FragmentOpeningNutritionBinding? = null
    private val binding get() = _binding!!

    interface OnMenuSelectedListener {
        fun onMenuSelected(isRecommendationMenu: Boolean)
    }

    private var menuSelectedListener: OnMenuSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMenuSelectedListener) {
            menuSelectedListener = context
        } else {
            throw RuntimeException("$context must implement OnMenuSelectedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOpeningNutritionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var selectedMenu: String? = null

        binding.recommendationMenuCard.setOnClickListener {
            selectedMenu = "rekomendasi"
            updateCardViewSelection(binding.recommendationMenuCard, binding.customMenuCard)
        }

        binding.customMenuCard.setOnClickListener {
            selectedMenu = "custom"
            updateCardViewSelection(binding.customMenuCard, binding.recommendationMenuCard)
        }

        binding.btnLogout.setOnClickListener {
            when (selectedMenu) {
                "rekomendasi" -> findNavController().navigate(R.id.action_openingNutritionFragment_to_nutritionFragment)
//                "custom" -> findNavController().navigate(R.id.action_openingNutritionFragment_to_customNutritionFragment)
                else -> {
                    // Show an error message or handle the case when no menu is selected
                }
            }
        }
    }

    private fun updateCardViewSelection(selectedCard: View, unselectedCard: View) {
        selectedCard.setBackgroundResource(R.drawable.selected_card_background)
        unselectedCard.setBackgroundResource(R.drawable.unselected_card_background)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}