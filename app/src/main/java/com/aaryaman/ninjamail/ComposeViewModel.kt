package com.aaryaman.ninjamail

import android.app.Application
import android.database.Cursor
import android.util.Log
import androidx.core.database.getStringOrNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.aaryaman.ninjamail.model.Contact
import com.aaryaman.ninjamail.model.ContactList

class ComposeViewModel(val app: Application) : AndroidViewModel(app) {

    val contactLists = MutableLiveData<List<ContactList>>()


    fun addContact() {

     refreshList()
    }


   private fun refreshList(){

        try {
            val cursor= LoadQuery()

            val itemNames = mutableListOf<String?>()
            val itemEmails = mutableListOf<String>()
            val itemGroups = mutableListOf<String?>()
            with(cursor) {
                while (moveToNext()) {
                    val itemName = getStringOrNull(getColumnIndexOrThrow("Name"))
                    val itemEmail = getString(getColumnIndexOrThrow("Email"))
                    val itemToday = getStringOrNull(getColumnIndexOrThrow("ContactGroup"))
                    itemNames.add(itemName)
                    itemEmails.add(itemEmail)
                    itemGroups.add(itemToday)
                }
            }

            val contactsList = arrayListOf<Contact>()


            for( i in 0 until itemNames.size) run {
                val name = itemNames[i]
                val email = itemEmails[i]
                val group = itemGroups[i]
                val a= Contact(email, name, group)
                contactsList.add(a)
            }
            val data= ContactList(contactsList,"dsdsd","ContactList")
            contactLists.postValue(listOf(data))

        }

        catch (e: Exception){
            Log.e("TAG", e.message.toString())
        }


    }

    private fun LoadQuery(): Cursor {
        val dbManager = DbManager(app)
        return dbManager.Query()
    }

}