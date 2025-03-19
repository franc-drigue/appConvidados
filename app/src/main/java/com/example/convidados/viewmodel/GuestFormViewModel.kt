package com.example.convidados.viewmodel

import androidx.lifecycle.ViewModel
import com.example.convidados.repository.GuestRepository

class GuestFormViewModel: ViewModel() {

    fun getInstanceGuestRepository() {
         GuestRepository.getInstance()
    }
}