package com.example.note.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class FragmentUtils {

    companion object  {

        @JvmStatic
        fun addFragmentToActivity(manager: FragmentManager, fragment: Fragment, frameId: Int, tag: String ) {
            val transaction: FragmentTransaction = manager.beginTransaction()
            transaction.add(frameId, fragment, tag)
            transaction.addToBackStack(tag)
            transaction.commit()
        }

        @JvmStatic
        fun replaceFragmentToActivity(manager: FragmentManager, fragment: Fragment, frameId: Int) {
            val transaction: FragmentTransaction = manager.beginTransaction()
            transaction.replace(frameId, fragment)
            transaction.commit()
        }
    }
}