package ru.sectorsj.where_to_go.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.util.*

object DateInputMask {

    fun listenDateInputFrom(source: EditText) {
        source.addTextChangedListener(textWatcher(source))
    }

    private fun textWatcher(source: EditText): TextWatcher = object : TextWatcher {
        private var current = ""
        private val yyyymmmdd = "YYYYMMDD"
        private val cal: Calendar = Calendar.getInstance()

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s.toString() != current) {
                var clean: String = s.toString().replace("[^\\d.]|\\.".toRegex(), "")
                val cleanC = current.replace("[^\\d.]|\\.".toRegex(), "")
                val cl = clean.length
                var sel = cl
                var i = 2
                while (i <= cl && i < 6) {
                    sel++
                    i += 2
                }
                if (clean == cleanC) sel--
                if (clean.length < 8) {
                    clean += yyyymmmdd.substring(clean.length)
                } else {
                    var year = clean.substring(0, 4).toInt()
                    var mon = clean.substring(4, 6).toInt()
                    var day = clean.substring(6, 8).toInt()
                    mon = if (mon < 1) 1 else if (mon > 12) 12 else mon
                    cal[Calendar.MONTH] = mon - 1
                    year = if (year < 1900) 1900 else if (year > 2100) 2100 else year
                    cal[Calendar.YEAR] = year

                    day =
                        if (day > cal.getActualMaximum(Calendar.DATE)) cal.getActualMaximum(Calendar.DATE) else day
                    clean = String.format("%02d%02d%02d", year, mon, day)
                }
                clean = String.format(
                    "%s-%s-%s", clean.substring(0, 4),
                    clean.substring(4, 6),
                    clean.substring(6, 8)
                )
                sel = if (sel < 0) 0 else sel
                current = clean
                source.setText(current)
                source.setSelection(if (sel < current.length) sel else current.length)
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    }
}