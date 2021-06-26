package com.aaryaman.ninjamail.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import com.aaryaman.ninjamail.model.ContactList

@Dao
interface ContactListDao {

    @Insert
    fun addContactList(contactList: ContactList):Int
}