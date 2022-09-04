package trackwareschoolbus.parentschool.fragment.mykids;

import android.graphics.Color;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.request.RequestOptions;

import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.toolsV2.DrawableToolsV2;


public class MyKidsHolder_v2 extends RecyclerView.ViewHolder {


    AppCompatImageView student_img, active_circle_img;
    TextView student_name_txt, student_status;
    AppCompatImageView tracker_iv;
    View bootom_container,arrow_img;
    public MyKidsHolder_v2(View view) {
        super(view);
        student_img = itemView.findViewById(R.id.student_img);
        active_circle_img = itemView.findViewById(R.id.active_circle_img);
        student_name_txt = itemView.findViewById(R.id.student_name_txt);
        student_status = itemView.findViewById(R.id.student_status);
        tracker_iv = itemView.findViewById(R.id.tracker_iv);
        bootom_container = itemView.findViewById(R.id.bootom_container);
        arrow_img= itemView.findViewById(R.id.arrow_img);
    }


    public MyKidsHolder_v2 isActive(boolean isActive) {

        active_circle_img.setImageResource(isActive ?  R.drawable.kids_list_active_small_circle:R.drawable.kids_list_not_active_small_circle);
        DrawableToolsV2.loadDrawable(tracker_iv, isActive ?R.drawable.bus_track_active:R.drawable.bus_track_not_active);
        student_status.setTextColor(Color.parseColor(isActive ?"#414143":"#646468"));
        bootom_container.setBackgroundColor(Color.parseColor(isActive ?"#F7FFF0":"#FAFAFA"));
        student_status.setText(isActive?itemView.getContext().getString(R.string.round_is_active):itemView.getContext().getString(R.string.no_active_rounds));
        arrow_img.setVisibility(isActive?View.VISIBLE:View.GONE);

        return this;
    }


//    public MyKidsHolder_v2 loadStudentImage(String imageUrl) {
//        DrawableToolsV2.loadDrawable(student_img, imageUrl, DrawableToolsV2.GLIDE_SCALE_CIRCLE_CROP.error(R.drawable.img_student));
//
//        return this;
//    }


    public MyKidsHolder_v2 loadStudentImage(GlideUrl glideUrl) {
//        DrawableToolsV2.loadDrawable(student_img, imageUrl, DrawableToolsV2.GLIDE_SCALE_CIRCLE_CROP.error(R.drawable.img_student));
        new RequestOptions();
        Glide.with(student_img).load(glideUrl).apply(RequestOptions.circleCropTransform()).into(student_img);

        return this;
    }


}
