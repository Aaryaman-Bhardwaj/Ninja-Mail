package com.aaryaman.ninjamail

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_contact.*

class AddContact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)


        add_person.setOnClickListener {

            val dbManager= DbManager(this)

            val values= ContentValues()
            values.put("Name", ed_name.text.toString())
            values.put("Email",ed_email.text.toString())
            values.put("ContactGroup", sp_group.selectedItem.toString())

            val ID= dbManager.InsertRegularTask(values)

            if (ID>0)
                Toast.makeText(this, "Contact added ✅ \n Id- $ID", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "Error occurred ", Toast.LENGTH_SHORT).show()

            ed_name.text= "".toEditable()
            ed_email.text= "".toEditable()


        }

        }
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)


}