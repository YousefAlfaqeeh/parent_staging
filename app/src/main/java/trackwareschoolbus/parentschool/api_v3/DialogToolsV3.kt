package trackwareschoolbus.parentschool.api_v3

import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import trackwareschoolbus.parentschool.R
import trackwareschoolbus.parentschool.interfaceParent.OnActionDoneListener
import java.util.*


fun createDialog(
    context: Context,
    title: String? = null,
    message: String? = null,
    drawableIcon: Drawable? = null,
    drawableId: Int = 0,
    drawableColor: Int? = null,
    positiveButtonText: String? = null,
    onPositiveClick: ((DialogInterface?) -> Unit)? = null,
//    onPositiveClickListener: ((dialog: DialogInterface?, which: Int?) -> Unit)? = { _: DialogInterface?, _: Int? -> },
    negativeButtonText: String? = null,
    neutralButtonText: String? = null,
    addedView: View? = null,
    customViewLayoutId: Int? = -1,
    onShow: ((androidx.appcompat.app.AlertDialog) -> Unit)? = null,
    onNegativeClick: ((DialogInterface?) -> Unit)? = null,
    onNeutralClick: ((DialogInterface?) -> Unit)? = null,
    onDismissListener:DialogInterface.OnDismissListener?=null ,
//    onNegativeClickListener: ((dialog: DialogInterface, which: Int) -> Unit)? = { _: DialogInterface, _: Int -> },
): Dialog {
    val alertDialogTheme = R.style.AppTheme_AlertDialogTheme
    return MaterialAlertDialogBuilder(context, alertDialogTheme).apply {
        title?.let {
            setTitle(it)
        }
        message?.let {
            setMessage(it)
        }
        onDismissListener?.let {
            setOnDismissListener(it)
        }

        customViewLayoutId?.let {
            if (it != -1 && it != 0) {
//                background = ColorDrawable(Color.TRANSPARENT);
                setView(it)
            }
        }

        drawableIcon?.let { drawableIcon ->
            drawableColor?.let { drawableColor ->
                getTintedDrawable(
                    context = context,
                    drawableResId = drawableId,
                    drawableObject = drawableIcon,
                    colorResId = drawableColor
                )
            } ?: let {
                setIcon(drawableIcon)
            }
        }
        drawableId?.let { drawableId ->
            drawableColor?.let { drawableColor ->
                getTintedDrawable(context, drawableId, colorResId = drawableColor)
            } ?: let {
                setIcon(drawableId)
            }
        }

        positiveButtonText?.let { positiveButtonText ->
            setPositiveButton(positiveButtonText) { dialogInterface: DialogInterface?, _: Int? -> onPositiveClick?.let { it(dialogInterface) } }
        }

        negativeButtonText?.let { negativeButtonText ->
            setNegativeButton(negativeButtonText) { dialogInterface: DialogInterface?, _: Int? -> onNegativeClick?.let { it(dialogInterface) } }
        }
        neutralButtonText?.let { neutralButtonText ->
            setNeutralButton(neutralButtonText) { dialogInterface: DialogInterface?, _: Int? -> onNeutralClick?.let { it(dialogInterface) } }
        }
//        negativeButtonText?.let { negativeButtonText ->
//            setNegativeButton(negativeButtonText, onNegativeClickListener)
//        }
        addedView?.let {
            setView(it)
        }


//        setIcon(DrawableTools.createCircularProgressDrawable(context))
    }.create().apply {
        if (onShow!=null){
            setOnShowListener{
                onShow(this@apply)
            }
        }


    }

}



fun createSingleChoiceDialog(
    context: Context,
    title: String? = null,
    message: String? = null,
    drawableIcon: Drawable? = null,
    drawableId: Int? = null,
    drawableColor: Int? = null,
    positiveButtonText: String? = null,
    onPositiveClickListener: DialogInterface.OnClickListener? = null,
    negativeButtonText: String? = null,
    onNegativeClickListener: DialogInterface.OnClickListener? = null,
    items: ArrayList<String>? = null,
    onItemClicked: OnActionDoneListener<Int>? = null,
): Dialog {
    return MaterialAlertDialogBuilder(context, R.style.AppTheme_AlertDialogTheme).apply {
        items?.let {
            setItems(items.toTypedArray<String>(), { dialog, which ->
                onItemClicked?.OnActionDone(which)
            })
        }

        title?.let {
            setTitle(it)
        }
        message?.let {
            setMessage(it)
        }
        drawableIcon?.let { drawableIcon ->
            drawableColor?.let { drawableColor ->
                getTintedDrawable(
                    context,
                    drawableObject = drawableIcon,
                    colorResId = drawableColor
                )
            } ?: let {
                setIcon(drawableIcon)
            }
        }
        drawableId?.let { drawableId ->
            drawableColor?.let { drawableColor ->
                getTintedDrawable(context, drawableResId = drawableId, colorResId = drawableColor)
            } ?: let {
                setIcon(drawableId)
            }
        }

        positiveButtonText?.let { positiveButtonText ->
            setPositiveButton(positiveButtonText, onPositiveClickListener)
        }

        negativeButtonText?.let { negativeButtonText ->
            setNegativeButton(negativeButtonText, onNegativeClickListener)
        }

//        setIcon(DrawableTools.createCircularProgressDrawable(context))
    }.create()
}

