package com.example.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidados.infra.Const
import com.example.convidados.model.GuestModel

class GuestRepository private constructor(context: Context){


    private var guestDataBase =  GuestDataBase(context)

    // Singleton
    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if(!::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun insert(guest: GuestModel): Boolean {
       return  try {
             val db = guestDataBase.writableDatabase

             val presence = if (guest.presence) 1 else 0

             val values = ContentValues().apply {
                 put(Const.KEY.COLUMN_NAME, guest.name)
                 put(Const.KEY.COLUMN_PRESENCE, presence)
             }

             db.insert(Const.KEY.TABLE_NAME, null, values)
             true
         }catch (e: Exception) {
             false
         }
    }
}