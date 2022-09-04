package trackwareschoolbus.parentschool.fragment.dialog;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.basePage.BaseDialog;
import trackwareschoolbus.parentschool.utilityParent.UtilityParent;

/**
 * Created by  on 3/13/2017.
 */

public class KidsInfoDialog extends BaseDialog {

    TextView labSchoolName;
    TextView labActiveRound;
    TextView labTimeArive;
    TextView labStudentName;
    Button btnDone;
    ImageView imgCloseDialog;
    LinearLayout layOutKidsInfo;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public KidsInfoDialog(@NonNull Context context, String studentName, String driver_name, String attendance_name, String active_round_now) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_kids_info);
        getWindow().setBackgroundDrawableResource(R.drawable.kids_info_dialog_background);
        setCancelable(false);

        layOutKidsInfo = (LinearLayout) findViewById(R.id.layOutKidsInfo);
        if (UtilityParent.getStringShared(UtilityParent.LANGUAGE).equals("ar")) {
            layOutKidsInfo.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        labStudentName = (TextView) findViewById(R.id.labNotificationDetails);
        labSchoolName = (TextView) findViewById(R.id.labSchoolName);
        labActiveRound = (TextView) findViewById(R.id.labActiveRound);
        labTimeArive = (TextView) findViewById(R.id.labTimeOTArrive);
       /* btnDone=(Button) findViewById(R.id.btnDone);*/
        imgCloseDialog = (ImageView) findViewById(R.id.imgCloseDialog);

        labStudentName.setText(studentName == null ? "" : studentName); // student name
        labSchoolName.setText(driver_name == null ? "" : driver_name); // driver_name
        labActiveRound.setText(attendance_name == null ? "" : attendance_name); // attendance_name
        labTimeArive.setText(active_round_now == null ? "" : active_round_now); // active_round_now

        imgCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
/*
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });*/
    }
}
