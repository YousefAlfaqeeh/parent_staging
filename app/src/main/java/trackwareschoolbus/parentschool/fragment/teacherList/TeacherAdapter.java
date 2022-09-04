//package trackwareschoolbus.parentschool.fragment.teacherList;
//
//import android.app.Activity;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Build;
//
//import androidx.annotation.RequiresApi;
//import androidx.core.content.ContextCompat;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.RequestOptions;
//
//import java.util.List;
//
//import trackwareschoolbus.parentschool.MainFragmentActivity;
//import trackwareschoolbus.parentschool.R;
//import trackwareschoolbus.parentschool.api_v3.GetKidsResp;
//import trackwareschoolbus.parentschool.bean.GCMMessage;
//import trackwareschoolbus.parentschool.bean.KidBean;
//import trackwareschoolbus.parentschool.bean.TeacherBean;
//import trackwareschoolbus.parentschool.dataBase.DAO;
//
//
///**
// * Created by  on 2/28/2017.
// */
//@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
//public class TeacherAdapter extends RecyclerView.Adapter<TeacherHolder> {
//
//
//    static Activity mActivity;
//    private List<TeacherBean> listTeacherBean;
//    private TeacherPresenter mPresenter;
//    private TeacherHolder holderCheck;
//    //    private ImageLoader imageLoader;
////    private DisplayImageOptions defaultOptions;
////    private ImageLoaderConfiguration config;
//    private GetKidsResp.Student kidBean;
//    private SQLiteDatabase database;
//
//    public TeacherAdapter(List<TeacherBean> listTeacherBean, GetKidsResp.Student kidBean, Activity mActivity, TeacherPresenter mPresenter, SQLiteDatabase database) {
//        this.kidBean = kidBean;
//        this.database = database;
//        this.listTeacherBean = listTeacherBean;
//        this.mActivity = mActivity;
//        this.mPresenter = mPresenter;
////        imageLoader = ImageLoader.getInstance();
////        defaultOptions = new DisplayImageOptions.Builder()
////                .cacheOnDisc(true).cacheInMemory(true)
////                .imageScaleType(ImageScaleType.EXACTLY)
////                .displayer(new FadeInBitmapDisplayer(300)).build();
//
////        config = new ImageLoaderConfiguration.Builder(Application.getInstance().getApplicationContext())
////                .defaultDisplayImageOptions(defaultOptions)
////                .memoryCache(new WeakMemoryCache())
////                .discCacheSize(100 * 1024 * 1024).build();
////        imageLoader.init(config);
//
//    }
//
//
//    @Override
//    public TeacherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_teacher, parent, false);
//        return new TeacherHolder(view);
//    }
//
//
//    @Override
//    public void onBindViewHolder(final TeacherHolder holder, final int position) {
//
//        final TeacherBean teacherBean = listTeacherBean.get(position);
//        if (teacherBean.getPhoto() != null && !teacherBean.getPhoto().equals("null") && !teacherBean.getPhoto().equals("")) {
//            Glide.with(mActivity).load(teacherBean.getPhoto().toString()).apply(new RequestOptions().fitCenter()).into(holder.imgTeacher);
////            imageLoader.displayImage((String) teacherBean.getPhoto().toString(), holder.imgTeacher);
//        } else {
//            holder.imgTeacher.setImageResource(R.drawable.img_student);
//        }
////        holder.labSubject.append(teacherBean.getSubject());
//        holder.labNameTeacher.setText(teacherBean.getName());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GCMMessage gcmmessage = new GCMMessage("", "", listTeacherBean.get(position).getClassRoomId(), kidBean.getId(), kidBean.getName(), listTeacherBean.get(position).getName(), listTeacherBean.get(position).getSchoolId(), listTeacherBean.get(position).getId(), kidBean.getFatherId(), kidBean.getMotherId());
//                MainFragmentActivity.showChatScreen(gcmmessage, listTeacherBean.get(position).getDeleted());
//
//            }
//        });
//        /**/
//        int notificationCount = DAO.haseNotification(database);
//        if (notificationCount > 0) {
//            holder.message_count_text.setVisibility(View.VISIBLE);
//            holder.message_count_text.setText(notificationCount + "");
//        } else
//            holder.message_count_text.setVisibility(View.GONE);
//        /**/
//        if (listTeacherBean.get(position).getDeleted())
//            holder.labNameTeacher.setTextColor(ContextCompat.getColor(holder.message_count_text.getContext(), R.color.grey_normal));
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return listTeacherBean.size();
//    }
//
//
//}
