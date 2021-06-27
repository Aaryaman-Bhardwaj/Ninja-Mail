package com.aaryaman.ninjamail.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aaryaman.ninjamail.R
import com.aaryaman.ninjamail.contactsList
import com.aaryaman.ninjamail.model.Contact
import kotlinx.android.synthetic.main.contact_list_item.view.*

class ContactRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContactListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.contact_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ContactListViewHolder -> {
                holder.bind(contactsList!![position])
            }
        }
    }

    override fun getItemCount(): Int {
        return contactsList!!.size
    }

}

class ContactListViewHolder constructor(itemView : View) : RecyclerView.ViewHolder(itemView) {

    val name = itemView.contact_name
    val email = itemView.contact_email
    val group = itemView.contact_group

    init {
        itemView.setOnClickListener {

        }
    }

    fun bind(contact: Contact) {
        name.text= contact.name
        email.text = contact.email
//        group.text = contact.group
        Log.e("TAG", contactsList.size.toString())
    }



}
