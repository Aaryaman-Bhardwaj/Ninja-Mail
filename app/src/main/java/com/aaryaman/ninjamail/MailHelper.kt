package com.aaryaman.ninjamail
//
import com.aaryaman.ninjamail.model.ContactList
import com.sendgrid.Method
import com.sendgrid.Request
import com.sendgrid.Response
import com.sendgrid.SendGrid
import com.sendgrid.helpers.mail.Mail
import com.sendgrid.helpers.mail.objects.Content
import com.sendgrid.helpers.mail.objects.Email
import java.io.IOException
import javax.security.auth.Subject


class Mail {





    fun sendMail(contactList: ContactList,from:Email){

    }




    fun sendEmail(from:Email, to:Email,subject: String,content: Content): Boolean {
        val mail = Mail(from, subject, to, content)

        val sg = SendGrid(KeyProvider.API_KEY)
        val request = Request()
        return try {
            request.method = Method.POST
            request.endpoint = "mail/send"
            request.body = mail.build()
            val response: Response = sg.api(request)
            println(response.statusCode)
            println(response.body)
            println(response.headers)
            true
        } catch (ex: IOException) {
            ex.printStackTrace()
            false
        }
    }
}