package com.aaryaman.ninjamail.model

import android.arch.persistence.room.PrimaryKey

data class Contact(@PrimaryKey val email:String, val name:String)