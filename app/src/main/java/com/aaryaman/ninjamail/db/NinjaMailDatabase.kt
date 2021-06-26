package com.aaryaman.ninjamail.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aaryaman.ninjamail.model.ContactList


@Database(entities = [(ContactList::class)], version = 1, exportSchema = false)
abstract class NinjaMailDatabase : RoomDatabase() {
    companion object {
        private var INSTANCE: NinjaMailDatabase? = null
        fun getDataBase(context: Context): NinjaMailDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, NinjaMailDatabase::class.java, "contacts-db")
                    .allowMainThreadQueries().build()
            }
            return INSTANCE as NinjaMailDatabase
        }
    }

    abstract fun daoContact(): ContactListDao
}