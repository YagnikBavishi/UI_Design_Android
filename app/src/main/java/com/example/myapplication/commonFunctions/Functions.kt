package com.example.myapplication.commonFunctions

import android.app.Activity
import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun spannableText(
    startIndex: Int,
    endIndex: Int,
    color: Int,
    string: String,
    context: Context,
    spannableCallBack: () -> Unit
): Spannable {

    val clickableSpan = object : ClickableSpan() {
        override fun onClick(view: View) {
            spannableCallBack()
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.color = ContextCompat.getColor(context, color)
            ds.isUnderlineText = false
        }
    }

    val spannable = SpannableString(string)
    spannable.setSpan(
        clickableSpan,
        startIndex, endIndex,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return spannable
}
