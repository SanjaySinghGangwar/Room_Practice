package com.theaverageguy.roompractice.utils

import android.content.Context
import android.text.Editable
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import java.util.regex.Pattern

object mToast {
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun isValidText(view: TextInputEditText?, text: Editable?): Boolean {
        return if (text?.length == 0) {
            view?.requestFocus()
            view?.error = "FIELD CANNOT BE EMPTY"
            false
        } else {
            true
        }
    }

    fun isValidPhoneNumber(view: TextInputEditText?, text: CharSequence?): Boolean {
        if (!Pattern.matches("[a-zA-Z]+", text)) {
            view?.requestFocus()
            view?.error = "Invalid"
            return false
        }
        return true
    }
}