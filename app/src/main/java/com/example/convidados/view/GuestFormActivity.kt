package com.example.convidados.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.R
import com.example.convidados.databinding.ActivityGuestFormBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.viewmodel.GuestFormViewModel
import com.google.android.material.snackbar.Snackbar

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var guestViewModel: GuestFormViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        guestViewModel = ViewModelProvider(this)[GuestFormViewModel::class.java]

        binding.buttonSave.setOnClickListener(this)
        binding.RadioPresent.isChecked = true
    }

    override fun onClick(view: View) {
        if (view.id == R.id.buttonSave){
            var inputName = binding.editName.text.toString()
            val presence = binding.RadioPresent.isChecked

            when(inputName){
                "" -> {
                    Snackbar.make(view, "Preencha o nome do convidado", Snackbar.LENGTH_LONG).show()
                    return
                }
                else -> {
                    Snackbar.make(view, "Usuário cadastrado", Snackbar.LENGTH_LONG).show()
                    binding.editName.text.clear()
                    binding.RadioPresent.isChecked = true
                }
            }

            guestViewModel.save(GuestModel(0, inputName, presence))
        }
    }
}