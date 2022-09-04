package trackwareschoolbus.parentschool.fragment.notification;

import androidx.core.text.HtmlCompat;
import androidx.core.text.HtmlKt;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.request.RequestOptions;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import trackwareschoolbus.parentschool.MainFragmentActivity;
import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.bean.NotificationBean;
import trackwareschoolbus.parentschool.utilityParent.DateTools;
import trackwareschoolbus.parentschool.utilityParent.UtilDialogs;

/**
 * Created by  on 2/23/2017.
 */


public class NotificationAdapter extends RecyclerView.Adapter<NotificationHolder> {

    List<NotificationBean> listNotificationBean = new ArrayList<>();
    MainFragmentActivity mActivity;
//    public com.nostra13.universalimageloader.core.ImageLoader imageLoader;
//    DisplayImageOptions defaultOptions;
//    ImageLoaderConfiguration config;


    public void clearData() {
        listNotificationBean.clear();
        notifyDataSetChanged();
    }

    public void addData(List<NotificationBean> mlistNotificationBean) {

        listNotificationBean.clear();
        listNotificationBean.addAll(mlistNotificationBean);
        notifyDataSetChanged();
    }

    public NotificationAdapter(MainFragmentActivity mActivity) {

        this.mActivity = mActivity;

//        Collections.sort(this.listNotificationBean, new Comparator<NotificationBean>() {
//            @Override
//            public int compare(NotificationBean lhs, NotificationBean rhs) {
//                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
//                return lhs.getDate().getTime() > rhs.getDate().getTime() ? 1 : 0;
//            }
//        });
//        imageLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
//        defaultOptions = new DisplayImageOptions.Builder()
//                .cacheOnDisc(true).cacheInMemory(true)
//                .imageScaleType(ImageScaleType.EXACTLY)
//                .displayer(new FadeInBitmapDisplayer(300)).build();

//        config = new ImageLoaderConfiguration.Builder(Application.getInstance().getApplicationContext())
//                .defaultDisplayImageOptions(defaultOptions)
//                .memoryCache(new WeakMemoryCache())
//                .discCacheSize(100 * 1024 * 1024).build();
//        imageLoader.init(config);


    }

    @Override
    public NotificationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notification, parent, false);
        return new NotificationHolder(view);
    }

    @Override
    public void onBindViewHolder(final NotificationHolder holder, int position) {
        final NotificationBean notificationBean = listNotificationBean.get(position);
//        String coloredStudentName = "<font color='#B01500'>" + notificationBean.getStudentName() + "</font>";
//        Spanned spanned = Html.fromHtml(notificationBean.getTitleAndText());
//        holder.labStudentName.setText(notificationBean.getTitleAndText());
//        new GlideUrl(notificationBean.getAvatar()) {
//            mapOf(new Pair("X-Openerp-Session-Id", sessionId))
//        }
        Glide.with(holder.imgStudent.getContext()).load(notificationBean.getAvatar()).apply(new RequestOptions().fitCenter()).into(holder.imgStudent);

        try {
            long dateLong = DateTools.Formats.DATE_FORMAT_GMT.parse(notificationBean.getDateTime()).getTime();

            holder.labStudentTime.setText("");
            if (DateUtils.isToday(dateLong)) {
                holder.labStudentTime.append(mActivity.getString(R.string.today));
                holder.labStudentTime.append("\n");
                holder.labStudentTime.append(mActivity.getString(R.string.at_time));
                holder.labStudentTime.append(" ");
                holder.labStudentTime.append(DateTools.Formats.TIMEONLY_FORMAT_12H_LOCAL.format(dateLong));
            } else {
                holder.labStudentTime.append(DateTools.Formats.DATEONLY_FORMAT_LOCAL.format(dateLong));
                holder.labStudentTime.append("\n");
                holder.labStudentTime.append(mActivity.getString(R.string.at_time));
                holder.labStudentTime.append(" ");
                holder.labStudentTime.append(DateTools.Formats.TIMEONLY_FORMAT_12H_LOCAL.format(dateLong));
            }
        } catch (ParseException e) {
            e.printStackTrace();
            holder.labStudentTime.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Spanned spanned = Html.fromHtml(notificationBean.getTitleAndTextForDialog());
                UtilDialogs.showGeneralMessageDialog(mActivity).setDialogeTitle(notificationBean.getNotificationsTitle()).setDialogeMessage(notificationBean.getNotificationsText()).loadImageFromURL(notificationBean.getAvatar());
            }
        });
        String coloredTitle = "<font color='#303F9F'> <b> " + notificationBean.getNotificationsTitle() + ": </b> </font>";
        String coloredDesc = "<font color='#B01500'> <i>" + notificationBean.getNotificationsText() + " </i> </font>";
        String htmlContent = coloredTitle + coloredDesc;
        holder.labNotificationDetails.setText(HtmlCompat.fromHtml(htmlContent,HtmlCompat.FROM_HTML_MODE_COMPACT));


        /**/

    }

    @Override
    public int getItemCount() {
        return listNotificationBean.size();
    }
}
