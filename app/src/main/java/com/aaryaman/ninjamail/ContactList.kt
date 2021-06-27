package com.aaryaman.ninjamail

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.database.getStringOrNull
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaryaman.ninjamail.model.Contact
import com.aaryaman.ninjamail.recycler.ContactRecyclerAdapter
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import kotlinx.android.synthetic.main.activity_contact_list.*
import kotlinx.android.synthetic.main.add_contact_layout.view.*
import java.util.*
import kotlin.collections.ArrayList

var contactsList : ArrayList<Contact> = ArrayList()


class ContactList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        add_contact.setOnClickListener {
           showAddDialog()
        }

        refreshList()


    }

    private fun showAddDialog() {
        MaterialDialog(this, BottomSheet(LayoutMode.WRAP_CONTENT)).show {

           customView(R.layout.add_contact_layout)
            getCustomView().apply {
                contact_add_db.setOnClickListener {
                    val email= email_field.text.toString()
                    val name=name_field.text.toString()
                    val dbManager= DbManager(it.context)

                    val values= ContentValues()
                    values.put("Name", name)
                    values.put("Email",email)

                    val ID= dbManager.InsertRegularTask(values)

                    if (ID>0)
                        Toast.makeText(email_field.context, "Contact added ✅ \n Id- $ID", Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(email_field.context, "Error occurred ", Toast.LENGTH_SHORT).show()
                    dismiss()
                    refreshList()
                }
            }

        }
        toolbar2.setNavigationOnClickListener {
            super.onBackPressed()
        }

    }


    fun refreshList(){

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

            contactsList = ArrayList()


            for( i in 0 until itemNames.size) run {
                val name = itemNames[i]
                val email = itemEmails[i]
                val group = itemGroups[i]
                val a= Contact(email, name, group)
                contactsList.add(a)
            }

            contact_recycler_view.apply {
                layoutManager = LinearLayoutManager(this@ContactList)
//                addItemDecoration(RvDecor(30))
                adapter = ContactRecyclerAdapter()
            }
        }

        catch (e: Exception){
            Toast.makeText(this, "NO Contacts saved", Toast.LENGTH_SHORT).show()
            Log.e("TAG", e.message.toString())
        }


    }

    private fun LoadQuery(): Cursor {
        val dbManager = DbManager(this)
        return dbManager.Query()
    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }
}