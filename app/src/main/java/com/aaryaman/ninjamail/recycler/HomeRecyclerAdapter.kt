package com.aaryaman.ninjamail.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aaryaman.ninjamail.R

class HomeRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RegularTaskListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.mail_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RegularTaskListViewHolder -> {
//                holder.bind(!![position])
            }
        }
    }

    override fun getItemCount(): Int {
//        return todaysTaskList!!.size
        return 5
    }

}

class RegularTaskListViewHolder constructor(itemView : View) : RecyclerView.ViewHolder(itemView) {

//    val box = itemView.task_name
//    val time = itemView.task_time

    init {
        itemView.setOnClickListener {

        }
    }

//    fun bind(task: Task) {
////        box.text= task.name
////        time.text = task.time
////        Log.e("TAG", todaysTaskList.size.toString())
//    }



}
