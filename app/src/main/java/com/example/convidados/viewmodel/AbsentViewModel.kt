package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class AbsentViewModel(application: Application) :  AndroidViewModel(application)  {
    private val repository = GuestRepository.getInstance(application.applicationContext)

    private val _listAllAbsent = MutableLiveData<List<GuestModel>>()

    val guestAbsent: LiveData<List<GuestModel>> = _listAllAbsent

    fun getAllAbsent() {
        _listAllAbsent.value = repository.getPresenceOrAbscent(false)
    }

    fun delete(id: Int) {
        repository.delete(id)
    }
}