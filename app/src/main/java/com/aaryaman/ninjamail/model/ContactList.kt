package com.aaryaman.ninjamail.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "ContactLists")
class ContactList(val list:List<Contact>, @PrimaryKey val id:String, val name:String)