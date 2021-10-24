package com.example.note.ui.addnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.note.R
import com.example.note.databinding.FragmentAddNoteBinding
import com.example.note.ui.category.CategoryDialogFragment
import com.example.note.utils.FragmentUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment: Fragment(), AdapterView.OnItemSelectedListener, View.OnClickListener {

     lateinit var binding: FragmentAddNoteBinding
     val viewModel: AddNoteViewModel by viewModels()

    private lateinit var spinnerCategory: Spinner
    private lateinit var textViewSubject: EditText
    private lateinit var textViewDone: TextView
    private lateinit var itemSelectedCategory: String
    private lateinit var buttonAddCategory: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false)
        binding.viewModel = viewModel
        setUpView()
        observeToLiveData()
        return binding.root
    }

    private fun setUpView() {
        spinnerCategory = binding.spinnerCategory
        textViewSubject = binding.subjectEditText
        buttonAddCategory = binding.buttonAddCat
        textViewDone = requireActivity().findViewById(R.id.textViewDone)
        binding.subjectEditText.requestFocus()
        textViewSubject.requestFocus()


        spinnerCategory.onItemSelectedListener = this
        textViewDone.setOnClickListener(this)
        buttonAddCategory.setOnClickListener(this)

    }

    private fun observeToLiveData() {
        viewModel.categorySpinnerData.observe(viewLifecycleOwner, Observer {
            var arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, it.map { newInt -> newInt.name })
            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            spinnerCategory.adapter = arrayAdapter
        })
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        itemSelectedCategory = parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View) {
      when(v.id) {
          R.id.buttonAddCat -> showAddCategoryDialog()
          R.id.textViewDone -> callSaveNote()
      }
    }

    private fun callSaveNote() {
        viewModel.saveNote(textViewSubject.text.toString(), itemSelectedCategory)
        refreshFragment()
    }

    private fun refreshFragment() {
        FragmentUtils.replaceFragmentToActivity(requireActivity()
            .supportFragmentManager, AddNoteFragment(),R.id.flFragment)
    }

    private fun showAddCategoryDialog() {
       CategoryDialogFragment.show(requireActivity().supportFragmentManager, "addCategory")
    }

}