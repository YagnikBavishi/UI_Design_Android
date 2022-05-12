package com.example.myapplication.activity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.example.myapplication.R
import com.example.myapplication.constants.Constants

class GenericTextWatcher(private val view: View, private val editText: Array<EditText>) :
    TextWatcher {
    override fun afterTextChanged(editable: Editable) {
        val text = editable.toString()
        when (view.id) {
            R.id.etFirstNumber -> {
                if (text.length == Constants.ONE) editText[Constants.ONE].requestFocus()
            }
            R.id.etSecondNumber -> {
                if (text.length == Constants.ONE) editText[Constants.TWO].requestFocus() else if (text.isEmpty()) editText[Constants.ZERO].requestFocus()
            }
            R.id.etThirdNumber -> {
                if (text.length == Constants.ONE) editText[Constants.THREE].requestFocus() else if (text.isEmpty()) editText[Constants.ONE].requestFocus()
            }
            R.id.etForthNumber -> {
                if (text.isEmpty()) editText[Constants.TWO].requestFocus()
            }
        }
    }

    override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
    override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
}