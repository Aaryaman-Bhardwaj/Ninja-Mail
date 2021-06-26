package com.aaryaman.ninjamail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class CreateMail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_mail)




    }

//    fun sendEmail() {
//        //Alternate way of instantiating
//        //SendGrid sendGrid = new SendGrid(SENDGRID_USERNAME,SENDGRID_PASSWORD);
//
//        val api: String? = ""
//        //Instantiate the object using your API key String
//        val sendgrid = SendGrid(api)
//        val email = SendGrid.Email()
//        email.addTo("1905827@kiit.ac.in")
//        email.from = "other@example.com"
//        email.subject = "Hello World"
//        email.text = "My first email with SendGrid Java!"
//        try {
//            val response = sendgrid.send(email)
//        } catch (e: SendGridException) {
//            Log.e("sendError", "Error sending email")
//        }
//    }
}