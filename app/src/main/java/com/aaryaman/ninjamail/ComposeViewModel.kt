package com.aaryaman.ninjamail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.aaryaman.ninjamail.db.NinjaMailDatabase
import com.aaryaman.ninjamail.model.ContactList

class ComposeViewModel(application: Application) : AndroidViewModel(application) {

    val contactLists = MutableLiveData<List<ContactList>>()
    val contactListDao = NinjaMailDatabase.getDataBase(application).daoContact()


    fun loadContacts() {
        val all = contactListDao.getAll()
        contactLists.postValue(all)
    }
}