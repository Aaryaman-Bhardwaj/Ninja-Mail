package com.aaryaman.ninjamail.db


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aaryaman.ninjamail.model.ContactList

@Dao
interface ContactListDao {

    @Insert
    fun addContactList(contactList: ContactList):Int

    @Query("SELECT * FROM contact_lists")
    fun getAll(): List<ContactList>
}