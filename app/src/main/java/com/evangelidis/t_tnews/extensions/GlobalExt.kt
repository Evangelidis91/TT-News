package com.evangelidis.t_tnews.extensions

import android.app.AlertDialog
import android.content.Context
import com.evangelidis.t_tnews.dialog.MainDialogHelper
import java.util.concurrent.TimeUnit

inline fun Context.showMainDialog(func: MainDialogHelper.() -> Unit): AlertDialog = MainDialogHelper(this).apply { func() }.create()

fun currentTimeInSeconds(): Long = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
