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




        }

        }
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)


}