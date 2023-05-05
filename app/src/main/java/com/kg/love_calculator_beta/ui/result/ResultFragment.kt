package com.kg.love_calculator_beta.ui.result

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kg.love_calculator_beta.R
import com.kg.love_calculator_beta.databinding.FragmentResultBinding
import com.kg.love_calculator_beta.ui.calculator.CalculatorFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@AndroidEntryPoint
@WithFragmentBindings
class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavigations()
        setArgs()
        initSimpleClicks()
    }

    private fun initSimpleClicks() {
        binding.apply {
            btnTryAgain.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setArgs() {
        binding.apply {
            tvFirstName.text = arguments?.getString(CalculatorFragment.KEY_FOR_FNAME)
            tvSecondName.text = arguments?.getString(CalculatorFragment.KEY_FOR_SNAME)
            tvResult.text = arguments?.getString(CalculatorFragment.KEY_FOR_RESULT)
            tvPercentage.text = arguments?.getString(CalculatorFragment.KEY_FOR_PERC) + "%"
        }
    }

    private fun initNavigations() {
        binding.apply {
            btnNavigateHome.setOnClickListener {
                findNavController().navigateUp()
            }
            btnNavigateHistory.setOnClickListener {
                findNavController().navigate(R.id.historyFragment)
            }
        }
    }
}