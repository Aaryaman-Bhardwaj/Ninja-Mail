package com.aaryaman.ninjamail
//
import com.aaryaman.ninjamail.mail.MailCount
import com.aaryaman.ninjamail.model.EmailRequest
import com.chibatching.kotpref.bulk
import com.sendgrid.Method
import com.sendgrid.Request
import com.sendgrid.Response
import com.sendgrid.SendGrid
import com.sendgrid.helpers.mail.Mail
import com.sendgrid.helpers.mail.objects.Content
import com.sendgrid.helpers.mail.objects.Email
import java.io.IOException
import java.lang.Exception
import javax.security.auth.Subject


object MailHelper {


    fun sendMail(emailRequest: EmailRequest) {
        val from = Email(emailRequest.fromEmail)
        val content = Content("text/html", emailRequest.body)
        emailRequest.contactList.list.forEach {
            val to = Email(it.email, it.name)
            sendEmail(from,to,emailRequest.subect,content)
        }
    }




    private fun sendEmail(from: Email, to: Email, subject: String, content: Content): Boolean {
        if (MailCount.count>100){
            Exception("Limit crossed of 100 ").printStackTrace()
            return true
        }
        MailCount.bulk {
            count++
        }
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