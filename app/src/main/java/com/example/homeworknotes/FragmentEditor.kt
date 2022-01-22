package com.example.homeworknotes

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
import android.widget.Toast
import com.example.homeworknotes.Constants.Companion.notes
import com.example.homeworknotes.fragments.MainFragment
import com.example.homeworknotes.models.DataNotes
import java.text.SimpleDateFormat
import java.util.*


class FragmentEditor : Fragment() {
    var formatDate = SimpleDateFormat("dd MMMM YYYY")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_editor, container, false)

        val nameEd: EditText = view.findViewById(R.id.NotesName)
        val description: EditText = view.findViewById(R.id.DescriptionShortNotes)
        val detDescription: EditText = view.findViewById(R.id.descriptionEditorNotes)
        val startDateTime: TextView = view.findViewById(R.id.startTimeEditor)
        val endDateTime: TextView = view.findViewById(R.id.endTimeEditor)
        val userNotes: EditText = view.findViewById(R.id.userNotes)
        val deleteBtn: Button = view.findViewById(R.id.deleteBtn)
        val saveBtn: Button = view.findViewById(R.id.saveBtn)

        val note = requireArguments().getSerializable("NOTES") as DataNotes

        nameEd.setText(note.nameUser)
        description.setText(note.shortDescription)
        detDescription.setText(note.detailedDescription)
        startDateTime.text = note.startDateTime
        endDateTime.text = note.endDateTime
        userNotes.setText(note.userNotes)

        startDateTime.setOnClickListener{
            val getDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(requireContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    val selectDate = Calendar.getInstance()
                    selectDate.set(Calendar.YEAR, year)
                    selectDate.set(Calendar.MONTH, monthOfYear)
                    selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val date = formatDate.format(selectDate.time)
                    startDateTime.text = date.toString()
                },
                getDate.get(Calendar.YEAR),
                getDate.get(Calendar.MONTH),
                getDate.get(Calendar.DAY_OF_MONTH))

            datePicker.show()
        }

        endDateTime.setOnClickListener {
            val getDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(requireContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    val selectDate = Calendar.getInstance()
                    selectDate.set(Calendar.YEAR, year)
                    selectDate.set(Calendar.MONTH, monthOfYear)
                    selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val date = formatDate.format(selectDate.time)
                    endDateTime.text = date.toString()
                },
                getDate.get(Calendar.YEAR),
                getDate.get(Calendar.MONTH),
                getDate.get(Calendar.DAY_OF_MONTH))

            datePicker.show()
        }


        saveBtn.setOnClickListener {
            for(item in notes){
                if (note.id == item.id){
                    item.nameUser = nameEd.text.toString()
                    item.shortDescription = description.text.toString()
                    item.detailedDescription = detDescription.text.toString()
                    item.startDateTime = startDateTime.text.toString()
                    item.endDateTime = endDateTime.text.toString()
                    item.userNotes = userNotes.text.toString()

                    Toast.makeText(activity, "Note update successfully", Toast.LENGTH_SHORT).show()

                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragment_container, MainFragment())?.commit()

                    Log.d("@@@@", "Update notes: ${item}")
                }
            }
        }
        return view

    }

}