package com.aaryaman.ninjamail.mail

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.aaryaman.ninjamail.MailHelper
import com.aaryaman.ninjamail.model.EmailRequest
import com.google.gson.Gson
import java.lang.Exception

class MailWorker(appContext: Context, workerParams: WorkerParameters): Worker(appContext, workerParams){

        companion object {
            const val EMAIL_REQUEST = "email.request"
            fun schedule(emailRequest: EmailRequest){

            }
        }

        override fun doWork(): Result {
            try {
                val emailData  = inputData.getString(EMAIL_REQUEST)
                val gson=Gson()
                val emailRequest = gson.fromJson(emailData, EmailRequest::class.java)
                MailHelper.sendMail(emailRequest)
                return Result.success()
            }catch (e:Exception){
                e.printStackTrace()
            }
            return Result.failure()
        }




}