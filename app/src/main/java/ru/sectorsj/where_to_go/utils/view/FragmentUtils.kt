package ru.sectorsj.where_to_go.utils.view

import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import ru.sectorsj.where_to_go.R

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.checkEditFields(view: View): Boolean {
    (view as? ViewGroup)?.forEach {
        if (it is ViewGroup) {
            checkEditFields(it)
        } else if (it is EditText) {
            if (it.text.isBlank()) {
                showToast(getString(R.string.notify))
                return true
            }
        }
    }
    return false
}

