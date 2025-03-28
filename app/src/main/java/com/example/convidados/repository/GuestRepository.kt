package com.example.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidados.infra.Const
import com.example.convidados.model.GuestModel

class GuestRepository private constructor(context: Context) {


    private var guestDataBase = GuestDataBase(context)

    // Singleton
    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun insert(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues().apply {
                put(Const.KEY.COLUMN_NAME, guest.name)
                put(Const.KEY.COLUMN_PRESENCE, presence)
            }

            db.insert(Const.KEY.TABLE_NAME, null, values)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues().apply {
                put(Const.KEY.COLUMN_NAME, guest.name)
                put(Const.KEY.COLUMN_PRESENCE, presence)
            }

            val clause = "${Const.KEY.COLUMN_ID} = ?"
            val args = arrayOf(guest.id.toString())

            db.update(Const.KEY.TABLE_NAME, values, clause, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun delete(id: Int): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val clause = "${Const.KEY.COLUMN_ID} = ?"
            val agr = arrayOf(id.toString())

            db.delete(Const.KEY.TABLE_NAME, clause, agr)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAll(): List<GuestModel> {
        val list = mutableListOf<GuestModel>()
        val db = guestDataBase.readableDatabase

        val projection = arrayOf(
            Const.KEY.COLUMN_ID,
            Const.KEY.COLUMN_NAME,
            Const.KEY.COLUMN_PRESENCE
        )

        val cursor = db.query(Const.KEY.TABLE_NAME, projection, null, null, null, null, null)

        cursor?.use {
            while (it.moveToNext()) {
                val id = it.getInt(it.getColumnIndexOrThrow(Const.KEY.COLUMN_ID))
                val name = it.getString(it.getColumnIndexOrThrow(Const.KEY.COLUMN_NAME))
                val presence = it.getInt(it.getColumnIndexOrThrow(Const.KEY.COLUMN_PRESENCE))
                list.add(GuestModel(id, name, presence == 1))
            }
        }
       db.close()
       return list
    }

    fun getPresenceOrAbscent(presente: Boolean): List<GuestModel> {
        val list = mutableListOf<GuestModel>()
        val db = guestDataBase.readableDatabase

        val selection = "presence = ?"
        val selectionArgs = arrayOf((if (presente) 1 else 0).toString())
        val cursor = db.query(Const.KEY.TABLE_NAME, null, selection, selectionArgs, null, null, null)

        cursor?.use {
            while (it.moveToNext()) {
                val id = it.getInt(it.getColumnIndexOrThrow("id"))
                val name = it.getString(it.getColumnIndexOrThrow("nome"))
                val presence = it.getInt(it.getColumnIndexOrThrow("presente")) == 1
                list.add(GuestModel(id, name, presence))
            }
        }
        db.close()
        return list
    }
}