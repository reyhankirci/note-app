package com.example.note.ui.category

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.example.note.R
import com.example.note.databinding.LayoutAddCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryDialogFragment : DialogFragment(), DialogInterface.OnClickListener {

    lateinit var binding: LayoutAddCategoryBinding
    val viewModel: CategoryViewModel by viewModels()

    private lateinit var editTextCategoryName: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_add_category, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return  AlertDialog.Builder(requireActivity())
            .setView(requireActivity().layoutInflater.inflate(R.layout.layout_add_category, null))
            .setPositiveButton("Save", this)
            .setNegativeButton("Cancel", this)
            .create()

    }

    override fun onClick(dialog: DialogInterface, which: Int) {
        when(which)  {
            DialogInterface.BUTTON_POSITIVE -> addCategory()
            DialogInterface.BUTTON_NEGATIVE -> dialog.cancel()
        }
    }

    private fun addCategory() {
        editTextCategoryName = dialog!!.findViewById(R.id.editTextCategoryName)
        viewModel.addCategory(editTextCategoryName.text.toString())
    }

    companion object {
        private fun newInstance() = CategoryDialogFragment()

        fun show(fragmentManager: FragmentManager, tag: String) {
            newInstance().run {
                show(fragmentManager, tag)
            }
        }
    }
}