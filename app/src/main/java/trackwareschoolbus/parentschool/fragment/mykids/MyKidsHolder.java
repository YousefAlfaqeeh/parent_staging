package trackwareschoolbus.parentschool.fragment.mykids;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import trackwareschoolbus.parentschool.R;

/**
 * Created by  on 2/23/2017.
 */

public class MyKidsHolder extends RecyclerView.ViewHolder {


    TextView labStudentName;
    TextView labStudentGrade;
    TextView labSchoolName;
    TextView labSemesterTime;
    AppCompatButton message_count_text;
    View llQRCodeView,llLocation, llAbsent, llCallWith, llChat, lliPickup;
    AppCompatImageView imgStudent, imgMenu;


    public MyKidsHolder(View view) {
        super(view);
        labStudentName = itemView.findViewById(R.id.labNotificationDetails);
        labStudentGrade = itemView.findViewById(R.id.labStudentGrade);
        labSchoolName = itemView.findViewById(R.id.labSchoolName);
        llLocation = itemView.findViewById(R.id.llLocation);
        lliPickup = itemView.findViewById(R.id.lliPickup);
        llAbsent = itemView.findViewById(R.id.llAbsent);
        llCallWith = itemView.findViewById(R.id.llCallWith);
        llChat = itemView.findViewById(R.id.llChat);
        imgStudent = itemView.findViewById(R.id.imgStudent);
        labSemesterTime = itemView.findViewById(R.id.labSemesterTime);
        imgMenu = itemView.findViewById(R.id.imgMenu);
        message_count_text = itemView.findViewById(R.id.message_count_text);
        llQRCodeView = itemView.findViewById(R.id.llQRCodeView);
    }

}
