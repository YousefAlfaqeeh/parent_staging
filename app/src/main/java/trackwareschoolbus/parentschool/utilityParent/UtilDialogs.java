package trackwareschoolbus.parentschool.utilityParent;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRadioButton;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.basePage.BaseActivity;
import trackwareschoolbus.parentschool.interfaceParent.OnActionDoneListener;


public final class UtilDialogs {


//    public static void showSuccessMessage(Activity activity) {
//
//        new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE)
//                .setTitleText("Good job!")
//                .setContentText("You clicked the button!")
//                .show();
//
//    }


    private static MaterialDialog processDialog;
    //    private static MaterialDialog messageYesNoDialog;
    private static MaterialDialog webViewDialog;
    private static MaterialDialog callFatherMotherDialog;
    private static MaterialDialog reasonDialog;


    /**/
    private static ArrayList<MaterialDialog> toastMessage = new ArrayList<>();

    /**/
    public static class ProcessingDialog {


        public ProcessingDialog show(Context context, Integer stringId) {
            try {


                if (processDialog != null) {
                    processDialog.cancel();
                    processDialog.dismiss();
                    processDialog = null;
                }
            } catch (Exception e) {

            }
//            if (processDialog == null) {
            if (stringId == null)
                stringId = R.string.waiting;
            processDialog = new MaterialDialog.Builder(context)
                    .content(stringId)
                    .progress(true, 0)
                    .canceledOnTouchOutside(false)
                    .cancelable(false)
                    .build();
//            }

            processDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            processDialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.dialog_round_shape));
            processDialog.show();

            return this;
        }

        public void dismiss() {
            try {
                if (processDialog != null && processDialog.isShowing()) {
                    processDialog.dismiss();
                }
            } catch (Exception e) {

            }

        }
    }


    public static class MessageYesNoDialog {

        /**/
        private MaterialDialog messageYesNoDialog;
        public Button btnSubmint, btnCancel;
        public TextView dialoge_message, dialoge_title;
        public AppCompatImageView dialoge_image;
        public Context context;

        public MessageYesNoDialog show(Context context) {

            this.context = context;
            try {


                if (messageYesNoDialog != null) {
                    messageYesNoDialog.cancel();
                    messageYesNoDialog.dismiss();
                    messageYesNoDialog = null;
                }
            } catch (Exception e) {

            }


            messageYesNoDialog = new MaterialDialog.Builder(context).
                    canceledOnTouchOutside(false).
                    cancelable(false).theme(Theme.DARK).
                    customView(R.layout.dialog_yes_no_message_original, true).
                    autoDismiss(true).build();

            btnSubmint = messageYesNoDialog.getView().findViewById(R.id.btnSubmint);
            btnCancel = messageYesNoDialog.getView().findViewById(R.id.btnCancel);
            dialoge_message = (TextView) messageYesNoDialog.getView().findViewById(R.id.dialoge_message);
            dialoge_title = (TextView) messageYesNoDialog.getView().findViewById(R.id.dialoge_title);
            dialoge_image = (AppCompatImageView) messageYesNoDialog.getView().findViewById(R.id.dialoge_image);
            /**/
            View.OnClickListener clickListenerDismiss = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            };
            btnSubmint.setOnClickListener(clickListenerDismiss);
            btnCancel.setOnClickListener(clickListenerDismiss);
            /**/
            messageYesNoDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//            messageYesNoDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
            messageYesNoDialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.trans_to_dark_selector));


            messageYesNoDialog.show();


            return this;
        }

        public boolean isShowing() {
            return messageYesNoDialog.isShowing();

        }

        public MessageYesNoDialog setImageWithColor(int imageId, int color) {
            dialoge_image.setVisibility(View.VISIBLE);
            if (color == 0) {
                dialoge_image.setImageDrawable(ContextCompat.getDrawable(context, imageId));
            } else {
                dialoge_image.setImageDrawable(UtilityParent.getTintDrawable(context, imageId, ContextCompat.getColor(context, color)));
            }

            return this;
        }

        public MessageYesNoDialog setDialogeMessage(String message) {
            if (message ==null || message.equals(""))
                return this;
            dialoge_message.setVisibility(View.VISIBLE);
            dialoge_message.setText(message);
            return this;
        }

        public MessageYesNoDialog setDialogeMessage(int messageid) {
            dialoge_message.setVisibility(View.VISIBLE);
            dialoge_message.setText(messageid);
            return this;
        }

        public MessageYesNoDialog loadImageFromURL(String imageUrl) {
            dialoge_image.setVisibility(View.VISIBLE);
            Glide.with(context).load(imageUrl).apply(new RequestOptions().fitCenter()).into(dialoge_image);
            return this;
        }


        public MessageYesNoDialog setDialogeTitle(String title) {
            if (title.equals(""))
                return this;
            dialoge_title.setVisibility(View.VISIBLE);
            dialoge_title.setText(title);
            return this;
        }

        public MessageYesNoDialog setDialogeTitle(Spanned htmlTitle) {
            dialoge_title.setVisibility(View.VISIBLE);
            dialoge_title.setText(htmlTitle);
            return this;
        }

        public MessageYesNoDialog setDialogeTitleTextColor(int textColor) {
            dialoge_title.setVisibility(View.VISIBLE);
            dialoge_title.setTextColor(ContextCompat.getColor(context, textColor));
            return this;
        }

        public MessageYesNoDialog setDialogeTitle(int messageId) {
            dialoge_title.setVisibility(View.VISIBLE);
            dialoge_title.setText(messageId);
            return this;
        }

//        public MessageYesNoDialog hideCancelButton(boolean hide) {
//            btnCancel.setVisibility(hide ? View.GONE : View.VISIBLE);
//            return this;
//        }

        public MessageYesNoDialog hideMainImage() {
            dialoge_image.setVisibility(View.GONE);
            return this;
        }


        public MessageYesNoDialog setYesButtonText(int messageId) {
            btnSubmint.setText(messageId);
            return this;
        }

        public MessageYesNoDialog setCancelButtonText(int messageId) {
            btnCancel.setVisibility(View.VISIBLE);
            btnCancel.setText(messageId);
            return this;
        }


        public MessageYesNoDialog setCancelButtonClickListener(final OnActionDoneListener onActionDoneListener) {
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                    onActionDoneListener.OnActionDone(MessageYesNoDialog.this);
                }
            });
            return this;
        }

        public MessageYesNoDialog setYesButtonClickListener(final OnActionDoneListener onActionDoneListener) {
            btnSubmint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                    onActionDoneListener.OnActionDone(MessageYesNoDialog.this);
                }
            });
            return this;
        }


        public void dismiss() {
            try {
                if (messageYesNoDialog != null && messageYesNoDialog.isShowing()) {
                    messageYesNoDialog.dismiss();
                }
            } catch (Exception e) {

            }

        }

    }


    public static MessageYesNoDialog showGeneralMessageDialog(final Activity activity) {
        return new UtilDialogs.MessageYesNoDialog().show(activity)
                .setYesButtonText(R.string.ok);
    }

    public static MessageYesNoDialog showNoGPSDialog(final Activity activity) {
        return new UtilDialogs.MessageYesNoDialog().show(activity)
                .setDialogeTitle(R.string.no_gps_dialog_title)
                .setDialogeMessage(R.string.no_gps_dialog_desc)
                .setYesButtonText(R.string.ok)
                .setCancelButtonText(R.string.cancel)
                .setImageWithColor(R.drawable.gps_error, R.color.colorPrimary)
                .setYesButtonClickListener(new OnActionDoneListener<MessageYesNoDialog>() {
                    @Override
                    public void OnActionDone(MessageYesNoDialog o) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        activity.startActivity(intent);
                    }
                });
    }

    public static MessageYesNoDialog showGeneralErrorDialog(final Context activity) {
        try {
            return new UtilDialogs.MessageYesNoDialog().show(activity)
                    .setDialogeTitle(R.string.error_api)
                    .setImageWithColor(R.drawable.img_error, 0)
                    .setYesButtonText(R.string.ok) ;
        } catch (Exception e) {
            return null;
        }

    }

    public static MessageYesNoDialog showGeneralErrorDialogAndFinishActivity(final Activity activity) {
        try {
            return new UtilDialogs.MessageYesNoDialog().show(activity)
                    .setDialogeTitle(R.string.error_api)
                    .setImageWithColor(R.drawable.img_error, 0)
                    .setYesButtonText(R.string.ok)
                    .setYesButtonClickListener(new OnActionDoneListener() {
                        @Override
                        public void OnActionDone(Object o) {
                            activity.finish();
                        }
                    });
        } catch (Exception e) {
            return null;
        }

    }


    public static MessageYesNoDialog showSuccessMessage(final Activity activity) {
        return new UtilDialogs.MessageYesNoDialog().show(activity)
                .setYesButtonText(R.string.ok)
                .setImageWithColor(R.drawable.register_successfully, R.color.colorPrimary);
    }

    public static MessageYesNoDialog showNoImageMessage(final Activity activity) {
        return new UtilDialogs.MessageYesNoDialog().show(activity)
                .setYesButtonText(R.string.ok)
                .hideMainImage();
    }

    public static MessageYesNoDialog showYesCancelMessage(final Activity activity) {
        return new UtilDialogs.MessageYesNoDialog().show(activity)
                .setYesButtonText(R.string.yes)
                .setCancelButtonText(R.string.cancel);
    }

    public static class WebViewDialog {

        /**/

        public WebView webView;
        public Context context;
        private View loading_view;

        public WebViewDialog show(Context context) {

            this.context = context;
            try {


                if (webViewDialog != null) {
                    webViewDialog.cancel();
                    webViewDialog.dismiss();
                    webViewDialog = null;
                }
            } catch (Exception e) {

            }


            webViewDialog = new MaterialDialog.Builder(context).
                    canceledOnTouchOutside(true).
                    cancelable(true).theme(Theme.LIGHT).
                    customView(R.layout.dialog_web_view, false).
                    autoDismiss(true).build();


            webViewDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            webViewDialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.trans_to_dark_selector));
            /**/
            webViewDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT); //Optional


            webViewDialog.show();

            webViewDialog.getView().findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
            webView = webViewDialog.getView().findViewById(R.id.webView1);
            loading_view = webViewDialog.getView().findViewById(R.id.loading_view);
            return this;
        }


        public WebViewDialog initWebLink(String url) {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(url);
            webView.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {
                    loading_view.setVisibility(View.GONE);
                    webView.setVisibility(View.VISIBLE);
                }
            });
            return this;
        }

        public void dismiss() {
            try {
                if (webViewDialog != null && webViewDialog.isShowing()) {
                    webViewDialog.dismiss();
                }
            } catch (Exception e) {

            }

        }

    }


    public static class MultichoicesDialog {

        /**/
        private MaterialDialog multi_choices_dialog;
        public Button btnSubmint, btnCancel;
        public Context context;
        private Integer selected = -1;
        private AppCompatRadioButton the_whole_day_Radio, only_during_the_morning_Radio;

        public MultichoicesDialog show(Context context) {

            this.context = context;
            try {


                if (multi_choices_dialog != null) {
                    multi_choices_dialog.cancel();
                    multi_choices_dialog.dismiss();
                    multi_choices_dialog = null;
                }
            } catch (Exception e) {

            }


            multi_choices_dialog = new MaterialDialog.Builder(context).
                    canceledOnTouchOutside(false).
                    cancelable(false).theme(Theme.DARK).
                    customView(R.layout.dialog_multichoices, true).
                    autoDismiss(true).build();

            btnSubmint = multi_choices_dialog.getView().findViewById(R.id.btnSubmint);
            btnCancel = multi_choices_dialog.getView().findViewById(R.id.btnCancel);
            the_whole_day_Radio = multi_choices_dialog.getView().findViewById(R.id.the_whole_day_Radio);
            only_during_the_morning_Radio = multi_choices_dialog.getView().findViewById(R.id.only_during_the_morning_Radio);
            /**/
            View.OnClickListener clickListenerDismiss = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            };
            btnSubmint.setOnClickListener(clickListenerDismiss);
            btnCancel.setOnClickListener(clickListenerDismiss);
            /**/
            multi_choices_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//            multi_choices_dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
            multi_choices_dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.trans_to_dark_selector));


            multi_choices_dialog.show();


            return this;
        }

        public boolean isShowing() {
            return multi_choices_dialog.isShowing();

        }


        public MultichoicesDialog setYesButtonText(int messageId) {
            btnSubmint.setText(messageId);
            return this;
        }

        public MultichoicesDialog setCancelButtonText(int messageId) {
            btnCancel.setVisibility(View.VISIBLE);
            btnCancel.setText(messageId);
            return this;
        }


        public MultichoicesDialog setCancelButtonClickListener(final OnActionDoneListener<MultichoicesDialog> onActionDoneListener) {
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
            return this;
        }

        public MultichoicesDialog setYesButtonClickListener(final OnActionDoneListener<Integer> onActionDoneListener) {
            btnSubmint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                    if (the_whole_day_Radio.isChecked())
                        selected = 1;
                    else if (only_during_the_morning_Radio.isChecked())
                        selected = 2;
                    else{
                        UtilityParent.shakeViews(the_whole_day_Radio,only_during_the_morning_Radio);
                        return;
                    }

                        onActionDoneListener.OnActionDone(selected);


                }
            });
            return this;
        }


        public void dismiss() {
            try {
                if (multi_choices_dialog != null && multi_choices_dialog.isShowing()) {
                    multi_choices_dialog.dismiss();
                }
            } catch (Exception e) {

            }

        }

    }

    public static int getDaysDifference(Date fromDate, Date toDate)
    {
        if(fromDate==null||toDate==null)
            return 0;

        return (int)( (toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
    }

    public static DatePickerDialog showDatePickerDialog(BaseActivity activity, OnActionDoneListener<Integer> onActionDoneListener) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

                final Calendar toDate = Calendar.getInstance();
                toDate.set(Calendar.YEAR,year);
                toDate.set(Calendar.MONTH,monthOfYear);
                toDate.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                int noDays = getDaysDifference(new Date(),toDate.getTime() );
//                int noDays = UtilityParent.getCountOfDays(UtilityParent.getDateFormat("dd/MM/yyyy"), (dayOfMonth + "/" + monthOfYear + "/" + year));
                if (noDays > -1) {
                    onActionDoneListener.OnActionDone(noDays);
                } else {
                    UtilityParent.showMessage(activity, activity.getString(R.string.error), activity.getString(R.string.please_chose_date));
                }
                Log.v("noDays1",""+noDays );
                Log.v("noDays2",""+(dayOfMonth + "/" + monthOfYear + "/" + year));



            }
        });
        datePickerDialog.show(activity.getSupportFragmentManager(), "Datepickerdialog");

        return datePickerDialog;
    }


}

