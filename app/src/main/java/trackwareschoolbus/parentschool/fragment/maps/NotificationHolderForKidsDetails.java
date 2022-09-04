package trackwareschoolbus.parentschool.fragment.maps;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import trackwareschoolbus.parentschool.R;

/**
 * Created by  on 2/23/2017.
 */


public class NotificationHolderForKidsDetails extends RecyclerView.ViewHolder {

    TextView labStudentName;

    TextView labStudentTime;
    AppCompatImageView imgStudent;
//    View top_line, bottom_line;

    public NotificationHolderForKidsDetails(View itemView) {

        super(itemView);


        labStudentName = itemView.findViewById(R.id.labNotificationDetails);
        labStudentTime = itemView.findViewById(R.id.labStudentTime);
        imgStudent = itemView.findViewById(R.id.imgStudent);
//        top_line = itemView.findViewById(R.id.top_line);
//        bottom_line = itemView.findViewById(R.id.bottom_line);


    }


}
