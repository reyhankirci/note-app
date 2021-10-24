package com.example.note.ui.note

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.note.R
import com.example.note.data.local.room.entities.Note
import com.example.note.utils.DateFormatterUtils
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NoteAdapter(
    private val noteDataSet: List<Note>,
    private val noteViewHolderListener: NoteViewHolderListener
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    interface NoteViewHolderListener {
        fun onNoteItemTrash(note: Note)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val subjectTextView: TextView = view.findViewById(R.id.subjectText)
        private val dateTextView: TextView = view.findViewById(R.id.dateText)
        private val categoryNameTextView: TextView = view.findViewById(R.id.categoryName)
        private val imageViewTrash: ImageView = view.findViewById(R.id.imageViewTrash)

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(note: Note, noteViewHolderListener: NoteViewHolderListener) {
            subjectTextView.text = note.subject.toString()
            dateTextView.text = convertDate(DateFormatterUtils.convertDateToLocalDate(note.createDate))
            categoryNameTextView.text = note.categoryName
            imageViewTrash.setOnClickListener {
                noteViewHolderListener.onNoteItemTrash(note)
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        private fun convertDate(localDate: LocalDate): String {
            val dateFormatter = DateTimeFormatter.ofPattern("yyyy MM dd")
            return DateFormatterUtils.convertDateByDateTimeFormatter(localDate, dateFormatter)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.note_row_item, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(noteDataSet[position], noteViewHolderListener)
    }

    override fun getItemCount(): Int {
        return  noteDataSet.size
    }
}