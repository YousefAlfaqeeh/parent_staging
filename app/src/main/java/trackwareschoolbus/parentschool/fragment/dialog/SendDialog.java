//package trackwareschoolbus.parentschool.fragment.dialog;
//
//import android.content.Context;
//import android.os.CountDownTimer;
//import android.os.Handler;
//import android.support.annotation.NonNull;
//import android.view.View;
//import android.view.Window;
//import android.widget.Button;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import trackwareschoolbus.parentschool.MainFragmentActivity;
//import trackwareschoolbus.parentschool.R;
//import trackwareschoolbus.parentschool.basePage.BaseDialog;
//import trackwareschoolbus.parentschool.enums.EnumFragment;
//
///**
// * Created by  on 3/9/2017.
// */
//
//
//public class SendDialog extends BaseDialog {
//
//
//    ProgressBar progressBar;
//    TextView labSend;
//    CountDownTimer mCountDownTimer;
//
//    public Button btnOk;
//    int timer = 0;
//
//    public SendDialog(@NonNull Context context, String message, final EnumFragment enumFragment) {
//        super(context);
//
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setBackgroundDrawableResource(R.drawable.dialog_regtangel_round_shape);
//        setCancelable(false);
//        setContentView(R.layout.dialog_send_message);
//        labSend = (TextView) findViewById(R.id.labSend);
//        labSend.setText(message);
//        btnOk = (Button) findViewById(R.id.btnOk);
//
//        if (enumFragment == EnumFragment.REGISTER) {
//            btnOk.setText(R.string.log_in);
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        btnOk.performClick();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }, 1000);
//        } else if (enumFragment == EnumFragment.FORGET_PASSWORD) {
//            btnOk.setText(R.string.ok);
//        }
//
//        btnOk.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (enumFragment == EnumFragment.REGISTER) {
//                            MainFragmentActivity.showLogInFragment();
//                            dismiss();
//
//                        } else if (enumFragment == EnumFragment.CHANGE_LOCATION) {
//                            MainFragmentActivity.showMyKidsFragment();
//                            dismiss();
//                        } else if (enumFragment == EnumFragment.MAPS || enumFragment == EnumFragment.FORGET_PASSWORD) {
//                            dismiss();
//                        } else {
//                            dismiss();
//                        }
//
//
//                    }
//                });
//
//      /*  btnOk=(Button)findViewById(R.id.btnOk);
//        btnOk.setVisibility(View.INVISIBLE);
//        btnOk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
//
//        labSend=(TextView)findViewById(R.id.labSend);
//        labSend.setVisibility(View.INVISIBLE);
//        progressBar=(ProgressBar)findViewById(R.id.prograss);
//        if (FragmentType== EnumFragment.MAPS){
//            labSend.setVisibility(View.VISIBLE);
//            progressBar.setVisibility(View.INVISIBLE);
//            labSend.setText(R.string.bus_near);
//            btnOk.setVisibility(View.VISIBLE);
//        }else if(FragmentType== EnumFragment.MYKIDS) {
//
//            btnOk.setVisibility(View.INVISIBLE);
//            mCountDownTimer = new CountDownTimer(5000, 1000) {
//
//                @Override
//                public void onTick(long millisUntilFinished) {
//                    Log.v("Log_tag", "Tick of Progress" + timer + millisUntilFinished);
//                    timer++;
//                    progressBar.setProgress(timer);
//
//                }
//
//                @Override
//                public void onFinish() {
//                    //Do what you want
//                    timer++;
//                    progressBar.setProgress(timer);
//                    progressBar.setVisibility(View.INVISIBLE);
//                    labSend.setVisibility(View.VISIBLE);
//                    btnOk.setVisibility(View.VISIBLE);
//                }
//            };
//            mCountDownTimer.start();
//
//        }
//    }*/
//    }
//
//}
