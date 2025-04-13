package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class PresentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application.applicationContext)

    private val _allGuestPresence = MutableLiveData<List<GuestModel>>();

    val guetsPresence: LiveData<List<GuestModel>> = _allGuestPresence

    fun getAllPresence() {
       _allGuestPresence.value =  repository.getPresenceOrAbscent(true)
    }

    fun delete(id: Int) {
        repository.delete(id)
    }
}