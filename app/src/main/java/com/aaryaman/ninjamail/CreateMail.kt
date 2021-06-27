package com.aaryaman.ninjamail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.aaryaman.ninjamail.mail.MailWorker
import com.aaryaman.ninjamail.model.ContactList
import com.aaryaman.ninjamail.model.EmailRequest
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.dateTimePicker
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import jp.wasabeef.richeditor.RichEditor
import java.util.concurrent.TimeUnit


class CreateMail : AppCompatActivity() {

    lateinit var composeViewModel: ComposeViewModel
    var contactList:ContactList?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        composeViewModel = ViewModelProvider(this).get(ComposeViewModel::class.java)
        composeViewModel.addContact()
        setContentView(R.layout.activity_create_mail)


        init()
    }

    private fun init() {
        composeViewModel.contactLists.observe(this){
            contactList=it?.get(0)?:return@observe
        }
        val mEditor = findViewById<View>(R.id.editor) as RichEditor
        mEditor.setEditorFontSize(22)
        mEditor.setEditorFontColor(Color.BLACK)
        mEditor.setPadding(10, 10, 10, 10)
        //mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
        //mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
        mEditor.setPlaceholder("Write content here ...")
        //mEditor.setInputEnabled(false);

        //mEditor.setInputEnabled(false);
//        mEditor.setOnTextChangeListener(OnTextChangeListener { text -> mPreview.text = text })

        findViewById<View>(R.id.action_undo).setOnClickListener {
            mEditor.undo()
        }

        findViewById<View>(R.id.action_redo).setOnClickListener {
            mEditor.redo()

        }

        findViewById<View>(R.id.action_bold).setOnClickListener {

            mEditor.setBold()
        }

        findViewById<View>(R.id.action_italic).setOnClickListener {

            mEditor.setItalic()

        }

        findViewById<View>(R.id.action_subscript).setOnClickListener {

            mEditor.setSubscript()

        }

        findViewById<View>(R.id.action_superscript).setOnClickListener {

            mEditor.setSuperscript()

        }

        findViewById<View>(R.id.action_strikethrough).setOnClickListener {

            mEditor.setStrikeThrough()

        }

        findViewById<View>(R.id.action_underline).setOnClickListener {

            mEditor.setUnderline()

        }

        findViewById<View>(R.id.action_heading1).setOnClickListener {

            mEditor.setHeading(1)

        }

        findViewById<View>(R.id.action_heading2).setOnClickListener {

            mEditor.setHeading(2)

        }

        findViewById<View>(R.id.action_heading3).setOnClickListener {

            mEditor.setHeading(3)

        }

        findViewById<View>(R.id.action_heading4).setOnClickListener {

            mEditor.setHeading(4)

        }

        findViewById<View>(R.id.action_heading5).setOnClickListener {

            mEditor.setHeading(5)

        }

        findViewById<View>(R.id.action_heading6).setOnClickListener {

            mEditor.setHeading(6)

        }
        var isChanged = false
        findViewById<View>(R.id.action_txt_color).setOnClickListener {


            mEditor.setTextColor(if (isChanged) Color.BLACK else Color.RED)
            isChanged = !isChanged

        }
        var isChanged_bg = false
        findViewById<View>(R.id.action_bg_color).setOnClickListener {


            mEditor.setTextBackgroundColor(if (isChanged_bg) Color.TRANSPARENT else Color.YELLOW)
            isChanged_bg = !isChanged_bg

        }

        findViewById<View>(R.id.action_indent).setOnClickListener {

            mEditor.setIndent()

        }

        findViewById<View>(R.id.action_outdent).setOnClickListener {

            mEditor.setOutdent()

        }

        findViewById<View>(R.id.action_align_left).setOnClickListener {

            mEditor.setAlignLeft()

        }

        findViewById<View>(R.id.action_align_center).setOnClickListener {

            mEditor.setAlignCenter()

        }

        findViewById<View>(R.id.action_align_right).setOnClickListener {

            mEditor.setAlignRight()

        }

        findViewById<View>(R.id.action_blockquote).setOnClickListener {

            mEditor.setBlockquote()

        }

        findViewById<View>(R.id.action_insert_bullets).setOnClickListener {

            mEditor.setBullets()

        }

        findViewById<View>(R.id.action_insert_numbers).setOnClickListener {

            mEditor.setNumbers()

        }

        findViewById<View>(R.id.action_insert_image).setOnClickListener {

            mEditor.insertImage(
                "https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg",
                "dachshund", 320
            )

        }

        findViewById<View>(R.id.action_insert_youtube).setOnClickListener {

            mEditor.insertYoutubeVideo("https://www.youtube.com/embed/pS5peqApgUA")

        }

        findViewById<View>(R.id.action_insert_audio).setOnClickListener {

            mEditor.insertAudio("https://file-examples-com.github.io/uploads/2017/11/file_example_MP3_5MG.mp3")

        }

        findViewById<View>(R.id.action_insert_video).setOnClickListener {

            mEditor.insertVideo(
                "https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/1080/Big_Buck_Bunny_1080_10s_10MB.mp4",
                360
            )

        }

        findViewById<View>(R.id.action_insert_link).setOnClickListener {

            mEditor.insertLink("https://github.com/wasabeef", "wasabeef")

        }
        findViewById<View>(R.id.action_insert_checkbox).setOnClickListener {

            mEditor.insertTodo()

        }



        findViewById<MaterialButton>(R.id.schedule_post).setOnClickListener {
            val subjectLine = findViewById<EditText>(R.id.subject_field)
            val subject = subjectLine.text.toString()
            val body =mEditor.html
            when {
                contactList == null -> {
                    snackBar(it,"Please select a Contact List")
                }
                subject.isBlank() -> {
                    snackBar(it,"Please provide a valid subject")
                }
                body.isNullOrBlank() ->{
                    snackBar(it,"Please provide a valid Email Body")
                }
                else -> {
                    schedulePost(subject, contactList!!,mEditor.html)
                }
            }
        }

    }

    private fun schedulePost(subject: String, contactList: ContactList, html: String) {
        MaterialDialog(this).show {
            message(text = "Currently mails can only be scheduled on 30sec interval. Do you want to continue ? ")
            positiveButton(text = "Okay") { dialog ->
                scheduleWork(contactList, html, subject)
            }
            negativeButton(text = "Cancel") { dialog ->
              this.dismiss()
            }
        }
    }

    private fun scheduleWork(
        contactList: ContactList,
        html: String,
        subject: String
    ) {
        val emailRequest = EmailRequest(
            System.currentTimeMillis(),
            "aryaman.bharadwah@gmail.com",
            contactList,
            html,
            subject
        )
        val gson = Gson()
        val inputData =
            Data.Builder().putString(MailWorker.EMAIL_REQUEST, gson.toJson(emailRequest)).build()
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val work = PeriodicWorkRequestBuilder<MailWorker>(30, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .setInputData(inputData)
            .build()
        val workManager = WorkManager.getInstance()
        workManager.enqueueUniquePeriodicWork("mail", ExistingPeriodicWorkPolicy.REPLACE, work)
    }

    private fun snackBar(view: View, msg:String){
        Snackbar.make(view,msg,Snackbar.LENGTH_SHORT).setAction("Ok") {

        }.show()
    }


}