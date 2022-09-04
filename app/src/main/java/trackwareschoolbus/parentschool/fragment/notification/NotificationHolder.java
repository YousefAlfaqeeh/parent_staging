package trackwareschoolbus.parentschool.fragment.notification;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import trackwareschoolbus.parentschool.R;

/**
 * Created by  on 2/23/2017.
 */


public class NotificationHolder extends RecyclerView.ViewHolder {

    TextView labStudentName;

    TextView labStudentTime;
    AppCompatImageView imgStudent;
    TextView labNotificationDetails;

    public NotificationHolder(View itemView) {

        super(itemView);


        labStudentName = (TextView) itemView.findViewById(R.id.labNotificationDetails);
        labStudentTime = (TextView) itemView.findViewById(R.id.labStudentTime);
        imgStudent = (AppCompatImageView) itemView.findViewById(R.id.imgStudent);
        labNotificationDetails =  itemView.findViewById(R.id.labNotificationDetails);
    }


}
