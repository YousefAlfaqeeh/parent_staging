package trackwareschoolbus.parentschool.fragment.qr_code;

import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.google.gson.JsonObject;

import trackwareschoolbus.parentschool.API_new.APIs.ApiController;
import trackwareschoolbus.parentschool.API_new.APIs.Constants;
import trackwareschoolbus.parentschool.API_new.APIs.ListenersAndInterFaces.OnApiComplete;
import trackwareschoolbus.parentschool.MainFragmentActivity;
import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.app.Application;
import trackwareschoolbus.parentschool.basePage.BaseFragment;
import trackwareschoolbus.parentschool.interfaceParent.OnActionDoneListener;
import trackwareschoolbus.parentschool.utilityParent.StaticValue;
import trackwareschoolbus.parentschool.utilityParent.UtilDialogs;

/**
 * Created by  on 2/23/2017.
 */

public class QRCodeFragment extends BaseFragment {

    private QRCodeReaderView qrCodeReaderView;
    private int studentId = -1;
    private View hit_view, hint_done_view;
    boolean stopReading = true;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_qr, container, false);
        studentId = getArguments().getInt("studentID");
//                ((TextView) view.findViewById(R.id.barImage)).setVisibility(View.GONE);
//        ((TextView) view.findViewById(R.id.labTitle)).setText(getString(R.string.qr_code_title));

        qrCodeReaderView = (QRCodeReaderView) view.findViewById(R.id.qrCodeReaderView);

//        view.findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainFragmentActivity.showMyKidsFragment();
//            }
//        });
        view.findViewById(R.id.hint_done_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goneByFade(view.findViewById(R.id.hit_view));
                stopReading = false;
//                qrCodeReaderView.setTorchEnabled(true);
            }
        });


        initCamera();
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
//        qrCodeReaderView.stopCamera();
    }


    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    MainFragmentActivity.showMyKidsFragment();
                    return true;
                }
                return false;
            }
        });
//        if (qrCodeReaderView.st)
//        qrCodeReaderView.startCamera();

    }

    @Override
    protected boolean showToolBarNotification() {
        return false;
    }

    @Override
    protected boolean showToolBack() {
        return false;
    }

    @Override
    protected boolean showToolBarSettings() {
        return false;
    }

    @Override
    protected String initToolBarTitle() {
        return "";
    }

//    private ConfermYesNoDialog conferm_yes_no_dialog;


    protected void initCamera() {
        qrCodeReaderView.setOnQRCodeReadListener(new QRCodeReaderView.OnQRCodeReadListener() {
            @Override
            public void onQRCodeRead(final String text, PointF[] points) {
                if (!text.toString().trim().equals("") && !stopReading) {
                    stopReading = true;
                    /**/
                    UtilDialogs.showYesCancelMessage(getActivity()).setDialogeTitle(R.string.by_confirmation_this_message).setYesButtonClickListener(new OnActionDoneListener<UtilDialogs.MessageYesNoDialog>() {
                        @Override
                        public void OnActionDone(UtilDialogs.MessageYesNoDialog Action) {
                            sendQRToAPI(text);
                        }
                    }).setCancelButtonClickListener(new OnActionDoneListener<UtilDialogs.MessageYesNoDialog>() {
                        @Override
                        public void OnActionDone(UtilDialogs.MessageYesNoDialog Action) {
                            qrCodeReaderView.startCamera();
                            stopReading = false;
                        }
                    });
                    /**/
                    qrCodeReaderView.stopCamera();
                }


            }
        });
        qrCodeReaderView.setQRDecodingEnabled(true);
        qrCodeReaderView.setAutofocusInterval(2000L);
//        qrCodeReaderView.setTorchEnabled(true);
        qrCodeReaderView.forceAutoFocus();
        qrCodeReaderView.setBackCamera();
    }


    private void sendQRToAPI(String beaconMac) {
        if (StaticValue.progressDialog != null) {
            StaticValue.progressDialog.show();
        }
        ApiController.sendStudentBeacon(getActivity(), beaconMac, studentId, new OnApiComplete<JsonObject>() {
            @Override
            public void onSuccess(JsonObject jsonObject) {
                try {
                    if (StaticValue.progressDialog != null) {
                        StaticValue.progressDialog.dismiss();
                    }
                    if (jsonObject.get("status").getAsString().toLowerCase().equals("ok")) {
                        UtilDialogs.showSuccessMessage(getMainActivity()).setDialogeTitle(R.string.beacon_successfully).setYesButtonClickListener(new OnActionDoneListener<UtilDialogs.MessageYesNoDialog>() {
                            @Override
                            public void OnActionDone(UtilDialogs.MessageYesNoDialog Action) {
                                MainFragmentActivity.showMyKidsFragment();
                            }
                        });

                    } else {

                        UtilDialogs.showGeneralErrorDialog(getMainActivity()).setDialogeTitle(R.string.this_device_is_not_certified).setYesButtonClickListener(new OnActionDoneListener<UtilDialogs.MessageYesNoDialog>() {
                            @Override
                            public void OnActionDone(UtilDialogs.MessageYesNoDialog Action) {
                                MainFragmentActivity.showMyKidsFragment();
                            }
                        });
                    }


                } catch (Exception e) {
                    onError(0, e.getStackTrace().toString());
                }

            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                if (StaticValue.progressDialog != null) {
                    StaticValue.progressDialog.dismiss();
                }
                Application.logEvents(Constants.Urls.SEND_STUDENT_BEACON, "QRCodeFragment - sendQRToAPI  ",errorMessage);

//                UtilityParent.showMessageDialog(mActivity, mActivity.getString(R.string.error), errorMessage);
                UtilDialogs.showGeneralErrorDialog(getMainActivity()).setDialogeMessage(R.string.api_send_error);
                qrCodeReaderView.startCamera();
                stopReading = false;

            }
        });
    }
}
