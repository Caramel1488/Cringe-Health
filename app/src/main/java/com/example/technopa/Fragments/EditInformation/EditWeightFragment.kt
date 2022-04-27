package com.example.technopa.Fragments.EditInformation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.technopa.Fragments.ProfileFragment
import com.example.technopa.MainActivity
import com.example.technopa.ViewModels.EditWeightVM
import com.example.technopa.ViewModels.ProfileVM
import com.example.technopa.databinding.EditWeightLayoutBinding
import com.example.technopa.models.User

class EditWeightFragment: DialogFragment() {

    private lateinit var binding: EditWeightLayoutBinding

    private val profileVM : ProfileVM by viewModels(ownerProducer = {requireParentFragment()})

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = EditWeightLayoutBinding.inflate(inflater, container,  false)

        profileVM.user.observe(viewLifecycleOwner){
            setNumberPickers(it)
        }

        binding.acceptButton.setOnClickListener {
            profileVM.setWeight(binding.weightNp1.value, binding.weightNp2.value)
            dismiss()
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        return binding.root

    }

    private fun setNumberPickers(user: User?) {
        binding.weightNp1.maxValue = 300
        binding.weightNp1.minValue = 0
        binding.weightNp1.value = user?.weight?.toInt() ?: 100

        binding.weightNp2.maxValue = 9
        binding.weightNp2.minValue = 0
        profileVM.weightNp2.observe(viewLifecycleOwner){
            binding.weightNp2.value = it
        }
    }

}