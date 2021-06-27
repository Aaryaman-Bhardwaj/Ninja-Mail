package com.aaryaman.ninjamail.model

import androidx.room.PrimaryKey


data class Contact(@PrimaryKey val email:String, val name:String, val group: String? = null)