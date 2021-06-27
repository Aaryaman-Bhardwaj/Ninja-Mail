package com.aaryaman.ninjamail
//

import com.aaryaman.ninjamail.mail.MailCount
import com.aaryaman.ninjamail.model.*
import com.chibatching.kotpref.bulk
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.util.Objects.requireNonNull


object MailHelper {
   val gson=Gson()

    fun sendMail(emailRequest: EmailRequest) {
        emailRequest.contactList.list.forEach {
            sendEmail(it.email,emailRequest)
        }

    }




    private fun sendEmail(to:String,emailRequest: EmailRequest): Boolean {
        if (MailCount.count>100){
            Exception("Limit crossed of 100 ").printStackTrace()
            return true
        }
        MailCount.bulk {
            count++
        }

        val content = Content("text/html", emailRequest.body)
        val fromMail = Mail(emailRequest.fromEmail, "")
        val toMail = Mail(to, "")
        val personalization = Personalization(emailRequest.subect, toMail)


        val emailFUll = EmailRequestSendgrid(content, fromMail, personalization, null)

        val client = OkHttpClient().newBuilder()
            .build()
        val mediaType = "application/json".toMediaTypeOrNull()
        val body: RequestBody = RequestBody.create(mediaType, gson.toJson(emailFUll) )
        val request = Request.Builder()
            .url("https://api.sendgrid.com/v3/mail/send")
            .method("POST", body)
            .addHeader("Authorization", "Bearer " + KeyProvider.API_KEY)
            .addHeader("Content-Type", "application/json")
            .build()
        val response = client.newCall(request).execute()

        val strBody = response.body?.string()
        val responseCode: Int = response.code
        response.close()
        return responseCode in 200..210;
    }
}