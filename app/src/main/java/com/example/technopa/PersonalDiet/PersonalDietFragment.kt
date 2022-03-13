package com.example.technopa.PersonalDiet

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technopa.R
import com.example.technopa.autoCleared
import kotlinx.android.synthetic.main.personal_diet_layout.*

class PersonalDietFragment : Fragment(R.layout.personal_diet_layout) {

    private val list:MutableList<PriemPishi> = mutableListOf(
        PriemPishi("Завтрак"),
        PriemPishi("Обед"),
        PriemPishi("Ужин")
    )

    private var adapterPersonalDiet:PersonalDietAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        buttonPersonalDietAdd.setOnClickListener {
            addDialog()
        }
    }

    fun init(){
        adapterPersonalDiet = PersonalDietAdapter()
        with(recyclerViewPersonalDiet){
            adapter = adapterPersonalDiet
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        adapterPersonalDiet.submitList(list)
    }


    fun addDialog(){
        AlertDialog.Builder(requireContext())
            .setMessage("Проводим технические работы по добавлению добавления")
            .setTitle("Не работает")
            .setPositiveButton("Понял",{_ , _ ->})
            .setNegativeButton("Не понял",{_ , _ ->addDialog()})
            .show()
    }



}