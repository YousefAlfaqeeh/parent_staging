package trackwareschoolbus.parentschool.gcmNotification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Random;

import trackwareschoolbus.parentschool.MainFragmentActivity;
import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.bean.ImportantNotification;
import trackwareschoolbus.parentschool.bean.NotificationObj;
import trackwareschoolbus.parentschool.dataBase.DAO;
import trackwareschoolbus.parentschool.dataBase.OpenHelper;
import trackwareschoolbus.parentschool.utilityParent.MyGson;

/**
 * Created by  on 6/6/2017.
 */

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class MyFirebaseGCMNotificationIntentService extends FirebaseMessagingService {


    public String getDataFromMessage(RemoteMessage remoteMessage) {
        try {
            Map<String, String> params = remoteMessage.getData();
            JSONObject object = new JSONObject(params);
            return object.toString();
        } catch (Exception ignore) {
        }
        return null;

    }


    private final static String CHANNEL_ID = "my_channel_01";
    private final static String CHANNEL_NAME = "CHANNEL_NAME";


    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        wakeLockHandling();

        String message = getDataFromMessage(remoteMessage);
//
        if (message == null) {
            return;
        }

        NotificationObj notificationObj = MyGson.getGson().fromJson(message, new TypeToken<NotificationObj>() {
        }.getType());

        switch (notificationObj.getAction()) {
            case SCHOOL:
                sendNotificationBeebOnly(notificationObj);
                sendSchoolBrodCast(notificationObj);
                break;
            case UPDATE:
                sendRefreshMainScreenBrodCast();
                break;
            case DRIVER:
                sendNotificationBeebOnly(notificationObj);
                sendDriverBrodCast(notificationObj);
                break;
            case NEAR:
                Intent intent = new Intent("Notification_Message").putExtra("sendOutherBrodCast", "outher");
                sendBroadcast(intent);
                sendNotificationBeebOnly(notificationObj);
                break;
            case ARRIVE_ALARM:
                sendNotificationBeebOnly(notificationObj);
                sendDriverBrodCast(notificationObj);
                break;
            case CONFIRM_PICKUP:
                Intent intent_confirm_pickup = new Intent("Notification_Message").putExtra("CONFIRM_PICKUP", "CONFIRM_PICKUP");
                sendBroadcast(intent_confirm_pickup);
                sendNotificationBeebOnly(notificationObj);
                break;
            case OTHER:
                sendOutherBrodCast("outher");
                sendRefreshMainScreenBrodCast();
                sendNotificationBeebOnly(notificationObj);
                break;
        }


    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void sendNotification(NotificationObj notificationObj) {
        /**/
        String title = "";
        String description = "";
        /**/
        title = notificationObj.getTitle();
        description = notificationObj.getMessage();
//        bitMap = createNotificationImage(imageURL);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        Intent intent = new Intent(this, MainFragmentActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        /**/
        final int not_nu = generateRandom();
        /**/

        Uri parse = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.new_beeb);

        NotificationCompat.Builder notificationBuilder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = title;// The user-visible name of the channel.
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            mChannel.enableLights(true);
            mChannel.enableVibration(true);
            mChannel.setLightColor(Color.GREEN);
            mChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            mChannel.setSound(parse, new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).setUsage(AudioAttributes.USAGE_ALARM).build());


            try {
                notificationManager.createNotificationChannel(mChannel);
//                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), parse);
//                r.play();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);
        notificationBuilder.setSound(parse);
        notificationBuilder.setDefaults(Notification.DEFAULT_VIBRATE);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, not_nu, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setSmallIcon(R.drawable.ic_android_black_24dp);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setPriority(Notification.PRIORITY_MAX);
        notificationBuilder.setTicker(title);
//        try {
//            notificationBuilder.setLargeIcon(Glide.with(this).asBitmap().load(notificationObj.getAvatar()).submit().get());
//        }catch (Exception e){
//
//        }

        try {
            notificationBuilder.setLargeIcon(Glide.with(this).asBitmap().load(R.mipmap.default_student).submit().get());
        } catch (Exception e) {

        }


        notificationBuilder.setContentIntent(pendingIntent);
        notificationBuilder.setContentTitle(title).setStyle(new NotificationCompat.BigTextStyle().bigText(title));
        notificationBuilder.setContentText(description).setStyle(new NotificationCompat.BigTextStyle().bigText(description));


//        if (gcmmessage == null) {//from chat
//
//
//        } else if (gcmmessage instanceof GCMMessage) {
//            intent.putExtra("From_Notification", "Notification");
//            intent.putExtra("GCMMessage", ((GCMMessage) gcmmessage));
//        }
        /**/

        switch (notificationObj.getAction()) {
            case SCHOOL:
                intent.putExtra("SchoolMessageNotification", "SchoolMessageNotification");
                intent.putExtra("SchoolMessage", (notificationObj));
                break;
            case UPDATE:
                break;
            case DRIVER:
                intent.putExtra("DriverMessageNotification", "DriverMessageNotification");
                intent.putExtra("DriverMessage", (notificationObj));
                break;
            case NEAR:
//                notificationBuilder.setDeleteIntent(createOnDismissedIntent(this));
//                if (!Application.isActivityVisible()) {
//                    Intent intentStart = new Intent(this, RepeateNotificationBroadcastReceiver.class);
//                    intentStart.putExtra("RepeateNotificationBroadcastReceiver", false);
//                    sendBroadcast(intentStart);
//                }
                break;
            case OTHER:
                break;

        }

        /////////////

        Notification notification = null;
        notification = notificationBuilder.build();

        /**/
        /**/
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int smallIconViewId = getApplicationContext().getResources().getIdentifier("right_icon", "id", android.R.class.getPackage().getName());

                if (smallIconViewId != 0) {
                    if (notification.contentIntent != null)
                        notification.contentView.setViewVisibility(smallIconViewId, View.INVISIBLE);

                    if (notification.headsUpContentView != null)
                        notification.headsUpContentView.setViewVisibility(smallIconViewId, View.INVISIBLE);

                    if (notification.bigContentView != null)
                        notification.bigContentView.setViewVisibility(smallIconViewId, View.INVISIBLE);
                }
            } else {
                notification.priority = Notification.PRIORITY_MAX;
            }


        } catch (NullPointerException e) {

        }
        notificationManager.notify(not_nu, notification);

    }

    public int generateRandom() {
        Random random = new Random();
        return random.nextInt(9999 - 1000) + 1000;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void sendNotificationBeebOnly(NotificationObj notificationObj) {
        /**/
        if (notificationObj.getMessage() == null || notificationObj.getMessage().isEmpty()) {
            return;
        }
        String title = "";
        String description = "";
        /**/
        title = notificationObj.getTitle();
        description = notificationObj.getMessage();
//        bitMap = createNotificationImage(imageURL);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        /**/
        Intent intent = new Intent(this, MainFragmentActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        /**/
        final int not_nu = generateRandom();
        /**/

        Uri parse = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.new_beeb);

        NotificationCompat.Builder notificationBuilder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = title;// The user-visible name of the channel.
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            mChannel.enableLights(true);
            mChannel.enableVibration(true);
            mChannel.setLightColor(Color.GREEN);
            mChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            mChannel.setSound(parse, new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).setUsage(AudioAttributes.USAGE_ALARM).build());


            try {
                notificationManager.createNotificationChannel(mChannel);
//                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), parse);
//                r.play();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);
        notificationBuilder.setSound(parse);
        notificationBuilder.setDefaults(Notification.DEFAULT_VIBRATE);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, not_nu, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setSmallIcon(R.drawable.ic_android_black_24dp);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setPriority(Notification.PRIORITY_MAX);
        notificationBuilder.setTicker(title);
//        try {
//            notificationBuilder.setLargeIcon(Glide.with(this).asBitmap().load(notificationObj.getAvatar()).submit().get());
//        }catch (Exception e){
//
//        }

        try {
            notificationBuilder.setLargeIcon(Glide.with(this).asBitmap().load(R.mipmap.default_student).submit().get());
        } catch (Exception e) {

        }


        notificationBuilder.setContentIntent(pendingIntent);
        notificationBuilder.setContentTitle(title).setStyle(new NotificationCompat.BigTextStyle().bigText(title));
        notificationBuilder.setContentText(description).setStyle(new NotificationCompat.BigTextStyle().bigText(description));

        /////////////

        Notification notification = null;
        notification = notificationBuilder.build();

        /**/
        /**/
        try {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int smallIconViewId = getApplicationContext().getResources().getIdentifier("right_icon", "id", android.R.class.getPackage().getName());

                if (smallIconViewId != 0) {
                    if (notification.contentIntent != null)
                        notification.contentView.setViewVisibility(smallIconViewId, View.INVISIBLE);

                    if (notification.headsUpContentView != null)
                        notification.headsUpContentView.setViewVisibility(smallIconViewId, View.INVISIBLE);

                    if (notification.bigContentView != null)
                        notification.bigContentView.setViewVisibility(smallIconViewId, View.INVISIBLE);
                }
//            } else {
//                notification.priority = Notification.PRIORITY_MAX;
//            }


        } catch (NullPointerException e) {

        }
        notificationManager.notify(not_nu, notification);

    }


//    private PendingIntent createOnDismissedIntent(Context context) {
//        Intent intent = new Intent(context, RepeateNotificationBroadcastReceiver.class);
//        intent.putExtra("RepeateNotificationBroadcastReceiver", true);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 111, intent, 0);
//        return pendingIntent;
//    }

    private void sendDriverBrodCast(NotificationObj driverMessage) {
        int notificationId = generateRandom();
        DAO.addNotification(OpenHelper.getDatabase(this), notificationId, notificationId);
        DAO.ImportantNotificationTable.add(OpenHelper.getDatabase(this), new ImportantNotification().setId(notificationId).setMessage(driverMessage).setType(ImportantNotification.DRIVER));
        Intent intent = new Intent("Notification_Message");
        intent.putExtra("DriverMessage", driverMessage);
        sendBroadcast(intent);
    }

    private void sendSchoolBrodCast(NotificationObj schoolMessage) {
        int notificationId = generateRandom();
        DAO.addNotification(OpenHelper.getDatabase(this), notificationId, -notificationId);
        DAO.ImportantNotificationTable.add(OpenHelper.getDatabase(this), new ImportantNotification().setId(notificationId).setMessage(schoolMessage).setType(ImportantNotification.SCHOOL));
        Intent intent = new Intent("Notification_Message");
        intent.putExtra("SchoolMessage", schoolMessage);
        sendBroadcast(intent);
    }

    private void sendOutherBrodCast(String notification) {
        DAO.addNotification(OpenHelper.getDatabase(this), -101, -101);
        Intent intent = new Intent("Notification_Message");
        intent.putExtra("sendOutherBrodCast", notification);
        sendBroadcast(intent);
    }

    private void sendRefreshMainScreenBrodCast() {
//        DAO.addNotification(OpenHelper.getDatabase(this), -101, -101);
        Intent intent = new Intent("Notification_Message");
        intent.putExtra("parents_settings_update", "");
        sendBroadcast(intent);
    }


    private void wakeLockHandling() {
        try {
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            boolean isScreenOn = pm.isScreenOn();
            if (!isScreenOn) {
                PowerManager.WakeLock wl = null;
                try {
                    wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "parent:pLock");
                } catch (Exception e) {
                    wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "parent:pLock");
                }
                wl.acquire(10000);
                PowerManager.WakeLock wl_cpu = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "parent:pCpuLock");
                wl_cpu.acquire(10000);
            }
        } catch (Exception ignore) {
        }

    }
}
