package com.evangelidis.t_tnews.dialog

import android.app.AlertDialog
import android.content.Context
import android.text.SpannableString
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.evangelidis.t_tnews.databinding.DialogMainBinding
import com.evangelidis.t_tnews.extensions.goneIf

open class MainDialogHelper(private val context: Context) : BaseDialogHelper() {

    override val dialogView: DialogMainBinding by lazy { DialogMainBinding.inflate(LayoutInflater.from(context)) }

    override val builder: AlertDialog.Builder by lazy { AlertDialog.Builder(context).setView(dialogView.root) }

    open val titleTextView: TextView? by lazy { dialogView.dialogBaseTitle }
    open val messageTextView: TextView by lazy { dialogView.dialogBaseText }
    open val negativeButton: TextView? by lazy { dialogView.dialogBaseNegativeButton }
    open val positiveButton: TextView by lazy { dialogView.dialogBasePositiveButton }
    open val neutralButton: TextView? by lazy { dialogView.dialogBaseNeutralButton }
    open val imageView: ImageView by lazy { dialogView.dialogBaseIcon }
    open val doNotShowAgainTextView: TextView? by lazy { dialogView.dialogBaseDoNotShowMessageAgainText }
    open val doNotShowAgainCheckBox: CheckBox? by lazy { dialogView.dialogBaseDoNotShowMessageAgainCheckbox }

    override fun create(): AlertDialog {
        styleDialog()
        return super.create()
    }

    //region Setters

    var title: String? = ""
        set(value) {
            field = value
            titleTextView?.text = value
        }
    var spannableTitle: SpannableString? = SpannableString("")
        set(value) {
            field = value
            titleTextView?.setText(value, TextView.BufferType.SPANNABLE)
        }
    var message: String = ""
        set(value) {
            field = value
            messageTextView.text = value
        }
    var doNotShowAgain: String? = ""
        set(value) {
            field = value
            doNotShowAgainTextView?.text = value
        }

    private var negativeButtonText = ""
        set(value) {
            field = value
            negativeButton?.text = value
        }

    private var positiveButtonText = ""
        set(value) {
            field = value
            positiveButton.text = value
        }

    private var neutralButtonText = ""
        set(value) {
            field = value
            neutralButton?.text = value
        }

    /**
     * Set dialog negative button's text and clickListener
     */
    fun setNegativeButton(text: String, clickListener: (() -> Unit)? = null) {
        negativeButtonText = text
        negativeButton?.setDialogButtonClickListener(clickListener)
    }

    /**
     * Set dialog positive button's text and clickListener
     */
    open fun setPositiveButton(text: String, clickListener: (() -> Unit)? = null) {
        positiveButtonText = text
        positiveButton.setDialogButtonClickListener(clickListener)
    }

    /**
     * Set dialog neutral button's text and clickListener
     */
    open fun setNeutralButton(text: String, clickListener: (() -> Unit)? = null) {
        neutralButtonText = text
        neutralButton?.setDialogButtonClickListener(clickListener)
    }

    //endregion

    /**
     * Sets a click listener on the dialog views
     *
     * Along with the nullable lambda that will be invoked
     * the dialog will be dismissed.
     */
    private fun View.setDialogButtonClickListener(func: (() -> Unit)?) {
        setOnClickListener {
            func?.invoke()
            dialog.dismiss()
        }
    }

    /**
     * Responsible for showing/hiding elements in the dialog
     * depending on whether they have been set or not.
     */
    open fun styleDialog() {
        titleTextView?.goneIf { spannableTitle.isNullOrBlank() && title.isNullOrBlank() }
        doNotShowAgainTextView?.goneIf { doNotShowAgain.isNullOrBlank() }
        doNotShowAgainCheckBox?.goneIf { doNotShowAgain.isNullOrBlank() }
        positiveButton?.goneIf { positiveButtonText.isBlank() }
        negativeButton?.goneIf { negativeButtonText.isBlank() }
        neutralButton?.goneIf { neutralButtonText.isBlank() }
    }

    open fun setCentreTitle() { dialogView.dialogBaseTitle.gravity = Gravity.CENTER }

    open fun setBoldTitle() {
        // TextViewCompat.setTextAppearance(dialogView.dialogBaseTitle, R.style.ecpHeadingText300)
        // dialogView.dialogBaseTitle.setTextColor(context.color(R.color.ecp_blue))
    }

    open fun setCentreMessage() { dialogView.dialogBaseText.gravity = Gravity.CENTER }

    open fun setBoldMessage() {
        // TextViewCompat.setTextAppearance(dialogView.dialogBaseText, R.style.ecpHeadingText400)
        // dialogView.dialogBaseText.updateMargins(
        //    top = context.dimen(R.dimen.dialog_alert_text_margin_top),
        //    bottom = context.dimen(R.dimen.dialog_alert_text_margin_bottom)
        // )
    }
}
