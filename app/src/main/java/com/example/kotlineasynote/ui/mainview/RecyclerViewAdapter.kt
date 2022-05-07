package com.example.kotlineasynote.ui.mainview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.kotlineasynote.R
import com.example.kotlineasynote.entities.OneNote
import java.util.*

class RecyclerViewAdapter(
    var data: MutableList<OneNote> = listOf<OneNote>(
        OneNote(
            "",
            "",
            "",
            Date()
        )
    ).toMutableList()
) : Adapter<RecyclerViewAdapter.ViewHolder>() {


    interface ClickedNote {
        fun clicked(note: OneNote)
    }

    var clickedNote: ClickedNote? = null


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val description: TextView
        val date: TextView
        val valueTexx: TextView

        init {
            description = view.findViewById(R.id.id_item_description)
            date = view.findViewById(R.id.id_item_creation_date)
            valueTexx = view.findViewById(R.id.id_item_text)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_one_note, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        data[position].apply {
            description.also { holder.description.text = it }
            text.also { holder.valueTexx.text = it }
            date.also { holder.date.text = it.toString() }
        }

        holder.itemView.setOnClickListener {
            if (clickedNote!=null){
                clickedNote?.clicked(data[position])
            }
        }

    }

    override fun getItemCount() = data.size

}


