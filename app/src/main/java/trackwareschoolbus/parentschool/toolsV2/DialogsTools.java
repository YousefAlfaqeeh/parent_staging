package trackwareschoolbus.parentschool.toolsV2;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.api_v3.GetKidsResp;
import trackwareschoolbus.parentschool.basePage.BaseActivity;
import trackwareschoolbus.parentschool.basePage.BaseRecyclerViewAdapter_V2;
import trackwareschoolbus.parentschool.bean.FeedbackRequest;
import trackwareschoolbus.parentschool.interfaceParent.OnActionDoneListener;
import trackwareschoolbus.parentschool.utilityParent.StringUtil;
import trackwareschoolbus.parentschool.utilityParent.UtilityParent;


public final class DialogsTools {


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
    private static MaterialDialog checkPinDialog;

    private static MaterialDialog checkPhoneNumberDialog;

    public static class BaseMaterialDialog {
        /**/
        private MaterialDialog materialDialog;
        public Context context;
        public View feed_a_img, feed_b_img, feed_c_img, btnCancel, btnSubmint;
        public EditText feed_et;
        protected View.OnClickListener dismissClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        };


        public <T extends View> T findView(int id) {
            return materialDialog.getView().findViewById(id);
        }

        public BaseMaterialDialog show(Context context, int layoutId) {
            this.context = context;
            materialDialog = new MaterialDialog.Builder(context).
                    canceledOnTouchOutside(false).
                    cancelable(false).theme(Theme.DARK).
                    customView(layoutId, true).
                    autoDismiss(true).build();
            materialDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            materialDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            materialDialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.trans_to_dark_selector));
            materialDialog.show();
            return this;
        }

        public void dismiss() {
            try {
                if (materialDialog != null && materialDialog.isShowing()) {
                    materialDialog.dismiss();
                }
            } catch (Exception e) {

            }

        }

    }


    /**/
    private static ArrayList<MaterialDialog> toastMessage = new ArrayList<>();

    /**/
    public static class ProcessDialog {


        public static MaterialDialog show(Context context) {
            return show(context, null);
        }

        public static MaterialDialog show(Context context, Integer stringId) {
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
//                    .title(stringId)
                    .progress(true, 0)
                    .canceledOnTouchOutside(false)
                    .progressIndeterminateStyle(false)
                    .cancelable(false)
//                    .titleGravity(GravityEnum.CENTER)
//                    .contentGravity(GravityEnum.CENTER)
//                    .btnStackedGravity(GravityEnum.CENTER)
//                    .itemsGravity(GravityEnum.CENTER)
//                    .buttonsGravity(GravityEnum.CENTER)
                    .build();
//            }

            processDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            processDialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.dialog_round_shape));
            processDialog.show();

            return processDialog;
        }

        public static void hide() {
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
        public String string_title;

        public MessageYesNoDialog(Context context) {
            this.context = context;
            messageYesNoDialog = new MaterialDialog.Builder(this.context).
                    canceledOnTouchOutside(false).
                    cancelable(false).theme(Theme.DARK).
                    customView(R.layout.dialog_yes_no_message_original, true).
                    autoDismiss(true).build();

            btnSubmint = messageYesNoDialog.getView().findViewById(R.id.btnSubmint);
            btnCancel = messageYesNoDialog.getView().findViewById(R.id.btnCancel);
            dialoge_message = messageYesNoDialog.getView().findViewById(R.id.dialoge_message);
            dialoge_title = messageYesNoDialog.getView().findViewById(R.id.dialoge_title);
            dialoge_image = messageYesNoDialog.getView().findViewById(R.id.dialoge_image);
        }

        public MessageYesNoDialog show(Context context) {

            this.context = context;
            try {


                if (messageYesNoDialog != null) {
                    messageYesNoDialog.cancel();
                    messageYesNoDialog.dismiss();
//                    messageYesNoDialog = null;
                }
            } catch (Exception e) {

            }
//          initial value in constructor

            messageYesNoDialog = new MaterialDialog.Builder(this.context).
                    canceledOnTouchOutside(false).
                    cancelable(false).theme(Theme.DARK).
                    customView(R.layout.dialog_yes_no_message_original, true).
                    autoDismiss(true).build();

            btnSubmint = messageYesNoDialog.getView().findViewById(R.id.btnSubmint);
            btnCancel = messageYesNoDialog.getView().findViewById(R.id.btnCancel);
            dialoge_message = messageYesNoDialog.getView().findViewById(R.id.dialoge_message);
            dialoge_title = messageYesNoDialog.getView().findViewById(R.id.dialoge_title);
            dialoge_image = messageYesNoDialog.getView().findViewById(R.id.dialoge_image);
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


            if (!string_title.isEmpty())
                dialoge_title.setText(string_title);


            messageYesNoDialog.show();


            return this;
        }

        public boolean isShowing() {
            return messageYesNoDialog.isShowing();

        }

        public MessageYesNoDialog setImageWithColor(@DrawableRes int drawableResId, int color) {
            try {
                dialoge_image.setVisibility(View.VISIBLE);
                if (color == 0) {
                    dialoge_image.setImageDrawable(ContextCompat.getDrawable(context, drawableResId));
                } else {
                    dialoge_image.setImageDrawable(DrawableToolsV2.getTintedDrawable(context, drawableResId, ContextCompat.getColor(context, color)));
                }
            } catch (Exception e) {

            }
            return this;
        }

        public MessageYesNoDialog setDialogeMessage(String message) {
            if (message.equals(""))
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
            try {

                dialoge_title.setVisibility(View.VISIBLE);
                dialoge_title.setText(title);

            } catch (Exception e) {

            }

            return this;
        }

        public MessageYesNoDialog setString_title(String string_title) {
            this.string_title = string_title;
            if (string_title.equals(""))
                return this;
            dialoge_message.setVisibility(View.VISIBLE);
            dialoge_message.setText(string_title);
            return this;
        }

        public MessageYesNoDialog setDialogeTitle(Spanned htmlTitle) {
            try {
                dialoge_title.setVisibility(View.VISIBLE);
                dialoge_title.setText(htmlTitle);
            } catch (Exception e) {

            }

            return this;
        }

        public MessageYesNoDialog setDialogeTitleTextColor(int textColor) {
            try {
                dialoge_title.setVisibility(View.VISIBLE);
                dialoge_title.setTextColor(ContextCompat.getColor(context, textColor));
            } catch (Exception e) {

            }

            return this;
        }

        public MessageYesNoDialog setDialogeTitle(int messageId) {
            try {
                dialoge_title.setVisibility(View.VISIBLE);
                dialoge_title.setText(messageId);
            } catch (Exception e) {

            }

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
            try {
                btnSubmint.setText(messageId);
            } catch (Exception e) {

            }

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
        return new MessageYesNoDialog(activity).show(activity)
                .setYesButtonText(R.string.ok);
    }

    public static MessageYesNoDialog showNoGPSDialog(final Activity activity) {
        return new MessageYesNoDialog(activity).show(activity)
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

    public static MessageYesNoDialog showGeneralErrorDialog(final Context activity, @Nullable String message) {
        try {
//            String titleMessage = activity.getString(R.string.error_api);
            String titleMessage = activity.getString(R.string.error_user_name);

            if (message != null) {
                titleMessage = message;
            }

            return new MessageYesNoDialog(activity)
                    .setString_title(titleMessage)
                    .setDialogeTitleTextColor(activity.getColor(R.color.black))
                    .setDialogeTitle(titleMessage)
                    .setImageWithColor(R.drawable.img_error, activity.getColor(R.color.black))
                    .setYesButtonText(R.string.ok).show(activity);

        } catch (Exception e) {
            return null;
        }

    }

    public static MessageYesNoDialog showSuccessMessage(final Activity activity) {
        return new MessageYesNoDialog(activity).show(activity)
                .setYesButtonText(R.string.ok)
                .setImageWithColor(R.drawable.register_successfully, R.color.colorPrimary);
    }

    public static MessageYesNoDialog showNoImageMessage(final Activity activity) {
        return new MessageYesNoDialog(activity).show(activity)
                .setYesButtonText(R.string.ok)
                .hideMainImage();
    }

    public static MessageYesNoDialog showYesCancelMessage(final Activity activity) {
        return new MessageYesNoDialog(activity).show(activity)
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
                    else {
                        UtilityParent.shakeViews(the_whole_day_Radio, only_during_the_morning_Radio);
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


    public static class CheckPinDialog {

        /**/
        private View btnCancel, btnSubmint;
        private Context context;
        private OnActionDoneListener afterPinCheck;

        public CheckPinDialog show(Context context) {
            /**/
            dismiss();
            this.context = context;


            checkPinDialog = new MaterialDialog.Builder(context).
                    canceledOnTouchOutside(false).
                    cancelable(false).theme(Theme.LIGHT).
                    customView(R.layout.dialog_are_you_sure, true).
                    autoDismiss(true).build();

            btnCancel = checkPinDialog.getView().findViewById(R.id.btnCancel);
            btnSubmint = checkPinDialog.getView().findViewById(R.id.btnSubmint);

            /**/
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
            btnSubmint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                    checkPin();
                }
            });
            /**/
            checkPinDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            checkPinDialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.trans_to_dark_selector));
            checkPinDialog.show();
            return this;
        }


        public CheckPinDialog checkPin() {
            afterPinCheck.OnActionDone(true);
            return this;


        }

        public CheckPinDialog setOnPinSubmit(OnActionDoneListener afterPinCheck) {
            this.afterPinCheck = afterPinCheck;
            return this;
        }


        public void dismiss() {

            try {
                if (checkPinDialog != null) {
                    checkPinDialog.dismiss();
                    checkPinDialog.cancel();
                }
                checkPinDialog = null;
            } catch (Exception e) {

            }


        }

    }


    public static DatePickerDialog showDatePickerDialog(BaseActivity activity, OnActionDoneListener<Integer> onActionDoneListener) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {


                int noDays = UtilityParent.getCountOfDays(UtilityParent.getDateFormat("dd/MM/yyyy"), new SimpleDateFormat("dd/MM/yyyy").format(UtilityParent.createCalendar(dayOfMonth, monthOfYear, year).getTime()));
                if (noDays > -1) {
                    onActionDoneListener.OnActionDone(noDays);
                } else {
                    UtilityParent.showMessage(activity, activity.getString(R.string.error), activity.getString(R.string.please_chose_date));
                }

            }
        });
        datePickerDialog.show(activity.getSupportFragmentManager(), "Datepickerdialog");

        return datePickerDialog;
    }

//    public static class MyDatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
//
//
////        public MyDatePickerFragment() {
////        }
//
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//            final Calendar c = Calendar.getInstance();
//            int year = c.get(Calendar.YEAR);
//            int month = c.get(Calendar.MONTH);
//            int day = c.get(Calendar.DAY_OF_MONTH);
//
//            return new DatePickerDialog(getActivity(), this, year, month, day);
//        }
//
//        public void onDateSet(android.widget.DatePicker view, int year, int month, int day) {
//            int months = view.getMonth() + 1;
//            if (months == 13) {
//                months = 1;
//            }
//            int noDays = UtilityParent.getCountOfDays(UtilityParent.getDateFormat("dd/MM/yyyy"), (view.getDayOfMonth() + "/" + months + "/" + view.getYear()));
//            if (noDays > -1) {
//                callAbsent(kidBean, noDays, selectedChoice);
//            } else {
//                UtilityParent.showMessage(mActivity, mActivity.getString(R.string.error), mActivity.getString(R.string.please_chose_date));
//            }
//
//        }
//
//
//    }


    public static class CallNumbersDialog {
        /**/
        private MaterialDialog call_numbers_dialog;
        public Context context;
        public View imgClose;
        public RecyclerView phones_RecyclerView;
        public BaseRecyclerViewAdapter_V2 kidsListAdapter;

        public CallNumbersDialog show(Context context) {

            this.context = context;
            try {


                if (call_numbers_dialog != null) {
                    call_numbers_dialog.cancel();
                    call_numbers_dialog.dismiss();
                    call_numbers_dialog = null;
                }
            } catch (Exception e) {

            }


            call_numbers_dialog = new MaterialDialog.Builder(context).
                    canceledOnTouchOutside(false).
                    cancelable(false).theme(Theme.DARK).
                    customView(R.layout.dialog_call_numbers, true).
                    autoDismiss(true).build();

            phones_RecyclerView = call_numbers_dialog.getView().findViewById(R.id.phones_RecyclerView);
            imgClose = call_numbers_dialog.getView().findViewById(R.id.imgClose);


            /**/
            imgClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
            /**/
            call_numbers_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//            messageYesNoDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
            call_numbers_dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.trans_to_dark_selector));


            call_numbers_dialog.show();
            return this;
        }

        public CallNumbersDialog initMobileNumbersAdaptert(List<GetKidsResp.Student.MobileNumber> mobileNumbers) {

//            int spanCount = mobileNumbers.size() > 1 ? 2 : 1;

            kidsListAdapter = new BaseRecyclerViewAdapter_V2<GetKidsResp.Student.MobileNumber, DialogCallNumbersHolder>(phones_RecyclerView, BaseRecyclerViewAdapter_V2.RecyclerViewType.Linear) {
                @Override
                public DialogCallNumbersHolder cViewHolder(ViewGroup viewGroup, int i, LayoutInflater layoutInflater) {
                    return new DialogCallNumbersHolder(layoutInflater.inflate(R.layout.dialog_call_numbers_cell, viewGroup, false));
                }

                @Override
                public void bViewHolder(@NonNull DialogCallNumbersHolder viewHolder, int i, GetKidsResp.Student.MobileNumber mobileNumber) {
                    /**/
                    StringBuilder phoneText = new StringBuilder("");
                    phoneText.append(mobileNumber.getName().trim());
                    phoneText.append("\n");
                    phoneText.append(mobileNumber.getMobile().trim());
                    viewHolder.phone_text.setText(phoneText.toString());
                    /**/
                    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            StringUtil.callThisNumber(context, mobileNumber.getMobile().trim());
                            call_numbers_dialog.dismiss();
                        }
                    });


                }
            }.withFadeAnimation();

            kidsListAdapter.addAll(mobileNumbers);
            return this;
        }

        class DialogCallNumbersHolder extends RecyclerView.ViewHolder {
            public AppCompatImageView phone_image;
            public TextView phone_text;

            public DialogCallNumbersHolder(View view) {
                super(view);
                phone_text = itemView.findViewById(R.id.phone_text);
            }

        }


        public void dismiss() {
            try {
                if (call_numbers_dialog != null && call_numbers_dialog.isShowing()) {
                    call_numbers_dialog.dismiss();
                }
            } catch (Exception e) {

            }

        }

    }

    public static class FeedBackDialog extends BaseMaterialDialog {
        /**/
        public View feed_a_img, feed_b_img, feed_c_img, btnCancel, btnSubmint;
        public EditText feed_et;
        private OnActionDoneListener onSubmintListener;

        public FeedBackDialog show(Context context) {
            show(context, R.layout.dialog_feed_back);
            feed_a_img = findView(R.id.feed_a_img);
            feed_b_img = findView(R.id.feed_b_img);
            feed_c_img = findView(R.id.feed_c_img);
            btnCancel = findView(R.id.btnCancel);
            btnSubmint = findView(R.id.btnSubmint);
            feed_et = findView(R.id.feed_et);

            /**/
            btnCancel.setOnClickListener(dismissClickListener);
            btnSubmint.setOnClickListener(dismissClickListener);

            View.OnClickListener selectClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    feed_a_img.setSelected(false);
                    feed_b_img.setSelected(false);
                    feed_c_img.setSelected(false);
                    v.setSelected(true);

                }
            };
            feed_a_img.setOnClickListener(selectClickListener);
            feed_b_img.setOnClickListener(selectClickListener);
            feed_c_img.setOnClickListener(selectClickListener);
            feed_a_img.setSelected(true);
            btnSubmint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String impression = "";

                    if (feed_a_img.isSelected())
                        impression = "very_good";
                    else if (feed_b_img.isSelected())
                        impression = "good";
                    else if (feed_c_img.isSelected())
                        impression = "bad";


                    FeedbackRequest feedbackRequest = new FeedbackRequest().setFeedBack(feed_et.getText().toString().trim()).setImpression(impression);
                    if (onSubmintListener != null)
                        onSubmintListener.OnActionDone(feedbackRequest);

                    dismiss();
                }
            });
            return this;
        }

        public FeedBackDialog setOnSubmintListener(OnActionDoneListener<FeedbackRequest> onSubmintListener) {
            this.onSubmintListener = onSubmintListener;
            return this;

        }
    }


}

