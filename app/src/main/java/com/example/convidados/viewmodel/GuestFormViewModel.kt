package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {
    private var repository =  GuestRepository.getInstance(application)

    fun save(guest: GuestModel) {
        repository.insert(guest)
    }
}