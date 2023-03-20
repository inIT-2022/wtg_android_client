package ru.sectorsj.where_to_go.utils.view

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

fun Fragment.setFullDescriptionButtonVisibility(btn: Button, textView: TextView) {
    val textLength = textView.text.length
    if (textLength < 200 || (textLength in 200..450)) {
        textView.maxLines = Int.MAX_VALUE
        btn.visibility = View.GONE
    }
}

fun Fragment.shareText(message: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, message)
        type = "text/plain"
    }
    Intent.createChooser(intent, "Message from WTG").apply {
        startActivity(this)
    }
}

