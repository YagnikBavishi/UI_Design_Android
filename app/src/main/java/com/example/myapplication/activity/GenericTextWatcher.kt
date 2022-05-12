package com.example.myapplication.activity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.example.myapplication.R

class GenericTextWatcher(private val view: View, private val editText: Array<EditText>) :
    TextWatcher {
    override fun afterTextChanged(editable: Editable) {
        val text = editable.toString()
        when (view.id) {
            R.id.etFirstNumber -> {
                if (text.length == 1) editText[1].requestFocus()
            }
            R.id.etSecondNumber -> {
                if (text.length == 1) editText[2].requestFocus() else if (text.isEmpty()) editText[0].requestFocus()
            }
            R.id.etThirdNumber -> {
                if (text.length == 1) editText[3].requestFocus() else if (text.isEmpty()) editText[1].requestFocus()
            }
            R.id.etForthNumber -> {
                if (text.isEmpty()) editText[2].requestFocus()
            }
        }
    }

    override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
    override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
}