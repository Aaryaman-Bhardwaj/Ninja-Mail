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
import com.aaryaman.ninjamail.model.ContactList
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.dateTimePicker
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import jp.wasabeef.richeditor.RichEditor
import kotlinx.android.synthetic.main.activity_create_mail.*


class CreateMail : AppCompatActivity() {

    lateinit var composeViewModel: ComposeViewModel
    var contactList:ContactList?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        composeViewModel = ViewModelProvider(this).get(ComposeViewModel::class.java)

        setContentView(R.layout.activity_create_mail)

        toolbar.setNavigationOnClickListener {
            super.onBackPressed()
        }


        init()
    }

    private fun init() {
        val mEditor = findViewById<View>(R.id.editor) as RichEditor
//        mEditor.setEditorHeight(200)
        mEditor.setEditorFontSize(22)
        mEditor.setEditorFontColor(Color.BLACK)
        //mEditor.setEditorBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundResource(R.drawable.bg);
        //mEditor.setEditorBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundResource(R.drawable.bg);
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


        val spinner = findViewById<Spinner>(R.id.contact_spiner)



        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                val data = composeViewModel.contactLists.value ?: return
                contactList=data[position]
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {

            }
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
            dateTimePicker(requireFutureDateTime = true) { materialDialog, dateTime ->

            }
        }
    }

    private fun snackBar(view: View, msg:String){
        Snackbar.make(view,msg,Snackbar.LENGTH_SHORT).setAction("Ok") {

        }.show()
    }


}