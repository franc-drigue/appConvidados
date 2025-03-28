package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application.applicationContext)

    // Variável que recebe a mudanças
    private val _listAllguest = MutableLiveData<List<GuestModel>>()

    // Variável que rebece a lista alterada
        // Essa variável é usada no observer, na Activity ou fragmento
    val guests: LiveData<List<GuestModel>> = _listAllguest

    fun getAll(){
        // listAllguest recebe o valor que vem da função getAll do repositório
       _listAllguest.value = repository.getAll()
    }

    fun delete(id: Int) {
        repository.delete(id)
    }
}