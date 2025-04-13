package com.example.convidados.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.convidados.infra.Const

class GuestDataBase(
    context: Context,
) : SQLiteOpenHelper(context, NAME, null, VERSION_DB) {

    companion object {
        private const val NAME = "GuestDb"
        private const val VERSION_DB = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Criação do banco
        val createTableGuest = """
            CREATE TABLE ${Const.KEY.TABLE_NAME} (
               ${Const.KEY.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
               ${Const.KEY.COLUMN_NAME} TEXT NOT NULL,
               ${Const.KEY.COLUMN_PRESENCE} INTEGER DEFAULT 0
            )
        """.trimIndent()

        db.execSQL(createTableGuest)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}