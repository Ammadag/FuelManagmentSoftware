package com.example.fuelmanagmentsoftware.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.view.Window
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fuelmanagmentsoftware.activities.recyclerview.OnItemClick
import com.example.fuelmanagmentsoftware.activities.recyclerview.RVAdapter
import com.example.fuelmanagmentsoftware.activities.room.UserInfo
import com.example.fuelmanagmentsoftware.activities.viewmodel.TodoViewModel
import com.example.fuelmanagmentsoftware.databinding.DialogBoxBinding
import com.example.fuelmanagmentsoftware.databinding.DialogFuelCostBinding
import com.example.fuelmanagmentsoftware.databinding.FragmentHomeBinding

class homeFrag : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var vM: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vM = ViewModelProvider(requireActivity())[TodoViewModel::class.java]
        clickListener()
        setupUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupUI() {
        binding.fragRv.layoutManager = GridLayoutManager(requireContext(), 2)
        vM.getData().observe(viewLifecycleOwner) {
            binding.fragRv.adapter = RVAdapter(it, object : OnItemClick {
                override fun onUpdate(userinfo: UserInfo) {
                    showAlertDialog(userinfo)
                }

                override fun onDelete(id: Int) {
                    vM.deleteItem(id)
                }
            })
        }
    }
    fun String.toEditable(): Editable {
        return Editable.Factory.getInstance().newEditable(this)
    }

    private fun clickListener() {
        binding.addTask.setOnClickListener {
            showAlertDialog(null)
        }
        binding.calBtn.setOnClickListener{
            showFuelCostDialog()
        }
    }

    private fun showAlertDialog(userinfo: UserInfo?) {
        val dialog = Dialog(requireContext())
        val dialogBinding = DialogBoxBinding.inflate(layoutInflater)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(false)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

        dialog.show()
        if (userinfo != null) {
            dialogBinding.name.editText?.text = userinfo.name.toEditable()
            dialogBinding.fuel.editText?.text = userinfo.fuel.toEditable()
            dialogBinding.date.editText?.text = userinfo.date.toEditable()
        }
        val name = dialogBinding.name.editText?.text
        val fuel = dialogBinding.fuel.editText?.text
        val date = dialogBinding.date.editText?.text
        dialogBinding.btnAdd.setOnClickListener {
            if (userinfo != null) {
                vM.updateData(UserInfo(userinfo.id, name.toString(), fuel.toString(), date.toString()))
                dialog.dismiss()
            } else {
                vM.insertData(UserInfo(0, name.toString(), fuel.toString(), date.toString()))
                dialog.dismiss()
            }
        }
        dialogBinding.cancelDialog.setOnClickListener {
            dialog.dismiss()
        }
    }
    private fun showFuelCostDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        val dialogBinding = DialogFuelCostBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        dialogBinding.btnCalculate.setOnClickListener {
            val fuelLiters = dialogBinding.etFuelLiters.text.toString().toFloatOrNull() ?: 0f
            val costPerLiter = dialogBinding.etCostPerLiter.text.toString().toFloatOrNull() ?: 0f
            val kmRan = dialogBinding.etKmRan.text.toString().toFloatOrNull() ?: 0f

            val avgCostPerLiter = (fuelLiters * costPerLiter) / kmRan
            val avgKmPerLiter = kmRan/ fuelLiters
            dialogBinding.tvResult.text = "Average Cost Per Liter: $avgCostPerLiter"
            if (fuelLiters != 0f && costPerLiter != 0f && kmRan != 0f) {
                dialogBinding.tvResult.text = "Average Cost Per Liter: $avgCostPerLiter"
                dialogBinding.tvResult.text = "Average Km/L: $avgKmPerLiter KM"
            } else {
                dialogBinding.tvResult.text = "Please enter valid values for all fields"
            }
        }

        dialog.show()
    }
}