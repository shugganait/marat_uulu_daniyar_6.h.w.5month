package com.kg.love_calculator_beta.ui.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kg.love_calculator_beta.ui.calculator.viewModel.CalcLoveViewModel
import com.kg.love_calculator_beta.R
import com.kg.love_calculator_beta.databinding.FragmentCalculatorBinding
import com.kg.love_calculator_beta.preference.Pref
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import javax.inject.Inject

@AndroidEntryPoint
@WithFragmentBindings
class CalculatorFragment: Fragment() {

    @Inject
    lateinit var prefH: Pref
    private lateinit var binding: FragmentCalculatorBinding
    private val viewModel: CalcLoveViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculatorBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBoardSection()
        initNavigations()
        initClickListeners()
    }

    private fun onBoardSection() {
        if (!prefH.isUserSeen()) {
            findNavController().navigate(R.id.onBoardFragment)
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

    private fun initClickListeners() {
        binding.apply {
            btnCalculate.setOnClickListener {
                if (etFirstName.text!!.isEmpty()) {
                    etFirstName.error = "field for first name is empty(("
                } else if (etSecondName.text!!.isEmpty()) {
                    etSecondName.error = "field for second name is empty(("
                } else {
                    viewModel.liveLove(etFirstName.text.toString(), etSecondName.text.toString()).observe(viewLifecycleOwner, Observer {
                        findNavController().navigate(
                            R.id.resultFragment,
                            bundleOf(
                                KEY_FOR_FNAME to it.firstName,
                                KEY_FOR_SNAME to it.secondName,
                                KEY_FOR_PERC to it.percentage,
                                KEY_FOR_RESULT to it.result
                            )
                        )
                    })
                    etFirstName.text!!.clear()
                    etSecondName.text!!.clear()
                }
            }
        }
    }

    companion object {
        const val KEY_FOR_FNAME = "fname"
        const val KEY_FOR_SNAME = "sname"
        const val KEY_FOR_PERC = "1000%"
        const val KEY_FOR_RESULT = "result"
    }
}