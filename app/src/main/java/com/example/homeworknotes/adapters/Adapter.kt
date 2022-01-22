package com.example.homeworknotes.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworknotes.FragmentEditor
import com.example.homeworknotes.R
import com.example.homeworknotes.models.DataNotes

class Adapter (private var array: ArrayList<DataNotes>, private val context: FragmentActivity) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name_notes)
        val shortDescription: TextView = view.findViewById(R.id.short_description)
        val detailedDescription: TextView = view.findViewById(R.id.detailed_description)
        val startDateEvent: TextView = view.findViewById(R.id.start_time_date)
        val endDateEvent: TextView = view.findViewById(R.id.end_time_date)
        val userNotes: TextView = view.findViewById(R.id.user_notes)
        val deleteBtn: Button = view.findViewById(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notes_fragment, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = array[position]
        holder.name.text = item.nameUser
        holder.shortDescription.text = item.shortDescription
        holder.detailedDescription.text = item.detailedDescription
        holder.startDateEvent.text = item.startDateTime
        holder.endDateEvent.text = item.endDateTime
        holder.userNotes.text = item.userNotes

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("NOTES", item)

            context.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentEditor::class.java, bundle)
                .addToBackStack(null).commit()
        }

        holder.deleteBtn.setOnClickListener {
            array.removeAt(position)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int {
        return array.size
    }
}