package com.aaryaman.ninjamail

import android.app.Application
import android.database.sqlite.SQLiteOpenHelper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.aaryaman.ninjamail.db.NinjaMailDatabase
import com.aaryaman.ninjamail.model.Contact
import com.aaryaman.ninjamail.model.ContactList

class ComposeViewModel(application: Application) : AndroidViewModel(application) {

    val contactLists = MutableLiveData<List<ContactList>>()


    fun loadContacts() {
        val data= listOf(ContactList(listOf(Contact("meghdut.windows@gmail.com","Meghdut")),"star","Megh"))
        contactLists.postValue(data)
    }
}