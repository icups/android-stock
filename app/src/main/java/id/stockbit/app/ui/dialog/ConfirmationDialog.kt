package id.stockbit.app.ui.dialog

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import id.stockbit.app.R

class ConfirmationDialog(context: Context, message: String = "Are you sure?", val actionListener: () -> Unit) : DialogInterface.OnClickListener {

    private val builder = AlertDialog.Builder(context, R.style.AppTheme_Alert_Dialog)

    init {
        builder.run {
            setMessage(message)
            setPositiveButton("YES", this@ConfirmationDialog)
            setNegativeButton("NO") { dialog, _ -> dialog.dismiss() }
            show()
        }
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        actionListener()
        dialog?.dismiss()
    }

}