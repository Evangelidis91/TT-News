package com.evangelidis.t_tnews.dialog

import android.app.AlertDialog
import com.evangelidis.t_tnews.databinding.DialogMainBinding

abstract class BaseDialogHelper {

    abstract val dialogView: DialogMainBinding
    abstract val builder: AlertDialog.Builder

    open var cancelable: Boolean = true
    open lateinit var dialog: AlertDialog

    open fun create(): AlertDialog {
        dialog = builder
            .setCancelable(cancelable)
            .create()

        return dialog
    }
}
