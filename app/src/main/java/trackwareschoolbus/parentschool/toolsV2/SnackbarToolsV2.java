package trackwareschoolbus.parentschool.toolsV2;//package parents_closer.parents_closer.utils;
//

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.StringRes;

import com.google.android.material.snackbar.Snackbar;

import trackwareschoolbus.parentschool.R;


public class SnackbarToolsV2 {


//    static final int textColor = R.color.white;
//    static final int scaleX = R.integer.scale_x;
//    static final int gravity = R.integer.start|center_vertical;

    static final int textColor = android.R.color.white;
    static final int scaleX = R.integer.scale_ar_en;
    static final int gravity = Gravity.START;


//    public static void showLoading(View view) {
//        if (view != null) {
//            Snackbar snackbar = Snackbar.make(view, view.getContext().getString(R.string.waiting), Snackbar.LENGTH_INDEFINITE);
//            TextView textView = getTextView(snackbar);
//            textView.setTextColor(view.getContext().getResources().getColor(textColor));
////            textView.setScaleX(view.getContext().getResources().getInteger(scaleX));
//            textView.setGravity(view.getContext().getResources().getInteger(gravity));
//            ProgressBar item = new ProgressBar(view.getContext());
//            ((ViewGroup) snackbar.getView().findViewById(R.id.snackbar_text).getParent()).addView(item,0);
//           ((CoordinatorLayout.LayoutParams)view.getLayoutParams()).gravity = Gravity.FILL_HORIZONTAL | Gravity.BOTTOM;
//            snackbar.show();
//        }
//    }


    public static void show(View view, @StringRes int stringRes) {
        if (view != null) {
            String message = view.getContext().getString(stringRes);
            if (!message.isEmpty()) {
                message = message.substring(0, 1).toUpperCase() + message.substring(1).toLowerCase();
            } else {
                message = "";
            }
            Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
            TextView textView = getTextView(snackbar);
            textView.setScaleX(1);
            textView.setTextColor(view.getContext().getResources().getColor(textColor));
//            textView.setScaleX(view.getContext().getResources().getInteger(scaleX));
            textView.setGravity(view.getContext().getResources().getInteger(gravity));
            snackbar.show();
        }
    }

    public static void show(Context context, View view, String message, @ColorRes int colorRes) {
        if (message != null && !message.isEmpty()) {
            message = message.substring(0, 1).toUpperCase() + message.substring(1).toLowerCase();
        } else {
            message = "";
        }
        if (view != null) {
            Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
            TextView textView = getTextView(snackbar);
            textView.setTextColor(context.getResources().getColor(colorRes));
//            textView.setScaleX(context.getResources().getInteger(scaleX));
            textView.setGravity(context.getResources().getInteger(gravity));
            snackbar.show();
        }
    }

    public static void show(View view, String message) {
        show(view.getContext(), view, message, textColor);
    }

//    public static void show( View view, @StringRes int stringRes) {
//        show(view, stringRes);
//    }

    public static void show(View view, String message, String actionStr, @ColorRes int actionColorRes, View.OnClickListener onClickListener) {
        if (view != null) {
            Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
            TextView textView = getTextView(snackbar);
//            textView.setScaleX(view.getContext().getResources().getInteger(scaleX));
            textView.setTextColor(view.getContext().getResources().getColor(textColor));
            textView.setGravity(view.getContext().getResources().getInteger(gravity));
            snackbar.setAction(actionStr, onClickListener);
            snackbar.setActionTextColor(view.getContext().getResources().getColor(actionColorRes));
            snackbar.show();
        }
    }

    private static TextView getTextView(Snackbar snackbar) {
        return ((TextView) snackbar.getView().findViewById(R.id.snackbar_text));
    }

    public static void show(View view, @StringRes int messageRes, @StringRes int actionStr, @ColorRes int actionColorRes, View.OnClickListener onClickListener) {
        if (view != null) {
            Snackbar snackbar = Snackbar.make(view, messageRes, Snackbar.LENGTH_LONG);
            TextView textView = getTextView(snackbar);
            textView.setTextColor(view.getContext().getResources().getColor(textColor));
//            textView.setScaleX(view.getContext().getResources().getInteger(scaleX));
            textView.setGravity(view.getContext().getResources().getInteger(gravity));
            snackbar.setAction(actionStr, onClickListener);
            snackbar.setActionTextColor(view.getContext().getResources().getColor(actionColorRes));
            snackbar.show();
        }
    }

//    public static void show(Context context, View view, @StringRes int messageRes, @StringRes int actionStr, View.OnClickListener onClickListener) {
//        show(context, view, messageRes, actionStr, textColor, onClickListener);
//    }

}
