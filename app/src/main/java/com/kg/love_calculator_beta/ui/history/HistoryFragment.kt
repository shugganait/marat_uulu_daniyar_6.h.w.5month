package com.kg.love_calculator_beta.ui.history

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kg.love_calculator_beta.db.localDB.AppDatabase
import com.kg.love_calculator_beta.R
import com.kg.love_calculator_beta.databinding.DialogLayoutBinding
import com.kg.love_calculator_beta.databinding.FragmentHistoryBinding
import com.kg.love_calculator_beta.model.Love
import com.kg.love_calculator_beta.ui.calculator.CalculatorFragment
import com.kg.love_calculator_beta.ui.calculator.viewModel.CalcLoveViewModel
import com.kg.love_calculator_beta.ui.history.adapter.LoveAdapter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import javax.inject.Inject

@AndroidEntryPoint
@WithFragmentBindings
class HistoryFragment : Fragment() {

    @Inject
    lateinit var db: AppDatabase
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var dialogBinding: DialogLayoutBinding
    private val adapter = LoveAdapter()
    private val viewModel: CalcLoveViewModel by viewModels()
//    private val daoVM: DaoViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        dialogBinding = DialogLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initNavigations()
        initListeners()
    }

    private fun initAdapter() {
        val data = db.loveDao().getAll()
        adapter.addList(data)
        binding.rvHistory.adapter = adapter
    }

    private fun changeSavedItem(
        id: Int,
        firstName: String,
        secondName: String,
        percentage: String
    ) {
        val data = Love(
            id = id,
            firstName = firstName,
            secondName = secondName,
            percentage = percentage
        )
        db.loveDao().update(data)//////////-----------
    }

    private fun showDialog(activity: Activity, id: Int?) {

        val dialog = Dialog(activity)
        dialog.setCancelable(true)
        dialog.setContentView(dialogBinding.root)

        dialogBinding.apply {
            btnOk.setOnClickListener{ i ->
                viewModel.liveLove(etFirstName.text.toString(), etSecondName.text.toString())
                    .observe(viewLifecycleOwner, Observer {
                        if (id != null) {
                            changeSavedItem(id, it.firstName, it.secondName, it.percentage)
                            findNavController().navigate(
                                R.id.resultFragment,
                                bundleOf(
                                    CalculatorFragment.KEY_FOR_FNAME to it.firstName,
                                    CalculatorFragment.KEY_FOR_SNAME to it.secondName,
                                    CalculatorFragment.KEY_FOR_PERC to it.percentage,
                                    CalculatorFragment.KEY_FOR_RESULT to it.result,
                                    "key_from_refactor" to true
                                )
                            )
                        }
                    })
                dialog.dismiss()
            }

            btnCancel.setOnClickListener {
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initListeners() {
        adapter.onItemClick = {
            showDialog(requireActivity(), it.id)
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