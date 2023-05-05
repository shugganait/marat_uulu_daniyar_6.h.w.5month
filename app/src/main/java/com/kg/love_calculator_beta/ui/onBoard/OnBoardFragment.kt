package com.kg.love_calculator_beta.ui.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kg.love_calculator_beta.R
import com.kg.love_calculator_beta.databinding.FragmentOnBoardBinding
import com.kg.love_calculator_beta.preference.PrefHelper
import com.kg.love_calculator_beta.ui.onBoard.adapter.OnBoardAdapter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import javax.inject.Inject

@AndroidEntryPoint
@WithFragmentBindings
class OnBoardFragment @Inject constructor(private val prefH: PrefHelper) : Fragment() {
    private lateinit var binding: FragmentOnBoardBinding
    private val adapter = OnBoardAdapter(this::onStartClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = adapter
    }

    private fun onStartClick() {
        prefH.saveSeen()
        findNavController().navigate(R.id.calculatorFragment)
    }
}