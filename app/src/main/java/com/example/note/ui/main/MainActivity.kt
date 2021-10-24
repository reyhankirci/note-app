package com.example.note.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.note.R
import com.example.note.databinding.ActivityMainBinding
import com.example.note.ui.addnote.AddNoteFragment
import com.example.note.ui.note.NoteFragment
import com.example.note.utils.FragmentUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by viewModels()

    lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpView()
    }

    private fun setUpView() {
        setSupportActionBar(binding.toolbarLayout)
        bottomNavView = binding.bottomNavView
        bottomNavView.setOnItemSelectedListener(this)

        showAddNoteFragment()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.addNote -> showAddNoteFragment()
            R.id.note -> showNoteFragment()
            else -> false
        }
    }

    private fun showNoteFragment(): Boolean {
        val fragment = NoteFragment()
        FragmentUtils.replaceFragmentToActivity(supportFragmentManager,
        fragment,
        R.id.flFragment)
        supportActionBar?.title = "Notes"
        return true
    }

    private fun showAddNoteFragment(): Boolean {
        val fragment = AddNoteFragment()
        FragmentUtils.replaceFragmentToActivity(supportFragmentManager,
            fragment,
            R.id.flFragment)
        supportActionBar?.title = "Add Note"
        return true
    }
}