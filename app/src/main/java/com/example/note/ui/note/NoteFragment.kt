package com.example.note.ui.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.note.R
import com.example.note.data.local.room.entities.Note
import com.example.note.databinding.FragmentNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : Fragment() {

    lateinit var binding: FragmentNoteBinding
    val viewModel: NoteViewModel by viewModels()

    lateinit var noteList: List<Note>

    private val noteViewHolderListener = object: NoteAdapter.NoteViewHolderListener {
        override fun onNoteItemTrash(note: Note) {
            viewModel.deleteNote(note)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false)
        binding.viewModel = viewModel

        observeToLiveData()
        return binding.root
    }

    private fun observeToLiveData() {
        viewModel.notesLiveData.observe(viewLifecycleOwner, Observer {
            noteList = it
            setNoteAdapter()
        })
    }

    private fun setNoteAdapter() {
        val noteAdapter = NoteAdapter(noteList, noteViewHolderListener)
        val recyclerview = binding.recyclerViewNote
        recyclerview.adapter = noteAdapter
    }
}