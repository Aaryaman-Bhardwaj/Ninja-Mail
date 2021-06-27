package com.aaryaman.ninjamail.model

data class EmailRequest(val id:Long,val fromEmail:String,val contactList: ContactList,val body:String,val subect:String)
