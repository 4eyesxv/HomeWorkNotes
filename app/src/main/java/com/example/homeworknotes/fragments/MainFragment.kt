package com.example.homeworknotes.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworknotes.Constants.Companion.counterId
import com.example.homeworknotes.Constants.Companion.notes
import com.example.homeworknotes.R
import com.example.homeworknotes.adapters.Adapter
import com.example.homeworknotes.models.DataNotes
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : Fragment() {
    lateinit var addNotesBtn: FloatingActionButton
    lateinit var recyclerView: RecyclerView

    var formatDate = SimpleDateFormat("dd MMMM YYYY")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        addNotesBtn = view.findViewById(R.id.addBtn)

        recyclerView = view.findViewById(R.id.recyclerView_main)
        recyclerView.adapter = Adapter(notes, requireActivity())

        addNotesBtn.setOnClickListener {
            addNotesDialog()
        }

        return view
    }

    private fun addNotesDialog() {
        val alert = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater.inflate(R.layout.create_notes, null)
        alert.setView(inflater)

        val addBtn: Button = inflater.findViewById(R.id.add_btn)

        val name = inflater.findViewById<EditText>(R.id.name_editText)
        val shortDescription = inflater.findViewById<EditText>(R.id.short_description_editText)
        val detailedDescription =
            inflater.findViewById<EditText>(R.id.detailed_description_editText)
        val tvStartDateEvent = inflater.findViewById<TextView>(R.id.tv_start_time)
        val tvEndDateEvent = inflater.findViewById<TextView>(R.id.tv_end_time)
        val userNotes = inflater.findViewById<EditText>(R.id.user_notes_editText)

        tvStartDateEvent.setOnClickListener {
            val getDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                requireContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    val selectDate = Calendar.getInstance()
                    selectDate.set(Calendar.YEAR, year)
                    selectDate.set(Calendar.MONTH, monthOfYear)
                    selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val date = formatDate.format(selectDate.time)
                    tvStartDateEvent.text = date.toString()
                },
                getDate.get(Calendar.YEAR),
                getDate.get(Calendar.MONTH),
                getDate.get(Calendar.DAY_OF_MONTH)
            )

            datePicker.show()
        }

        tvEndDateEvent.setOnClickListener {
            val getDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                requireContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                { view, year, monthOfYear, dayOfMonth ->
                    val selectDate = Calendar.getInstance()
                    selectDate.set(Calendar.YEAR, year)
                    selectDate.set(Calendar.MONTH, monthOfYear)
                    selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val date = formatDate.format(selectDate.time)
                    tvEndDateEvent.text = date.toString()
                },
                getDate.get(Calendar.YEAR),
                getDate.get(Calendar.MONTH),
                getDate.get(Calendar.DAY_OF_MONTH)
            )

            datePicker.show()
        }

        val dialog = alert.create()


        addBtn.setOnClickListener {
            notes.add(
                DataNotes(
                    counterId,
                    name.text.toString(),
                    shortDescription.text.toString(),
                    detailedDescription.text.toString(),
                    tvStartDateEvent.text.toString(),
                    tvEndDateEvent.text.toString(),
                    userNotes.text.toString(),
                )
            )
            counterId++
            Log.d("@@@@", "Added notes: ${notes}")
            dialog.dismiss()
        }
        dialog.show()
    }
}