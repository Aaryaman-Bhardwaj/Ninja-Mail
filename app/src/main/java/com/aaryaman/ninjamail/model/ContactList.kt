package com.aaryaman.ninjamail.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_lists")
class ContactList(val list:List<Contact>, @PrimaryKey val id:String, val name:String)