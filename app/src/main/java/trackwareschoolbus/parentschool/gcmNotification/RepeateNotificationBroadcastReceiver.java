//package trackwareschoolbus.parentschool.gcmNotification;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.media.AudioManager;
//import android.media.MediaPlayer;
//import android.net.Uri;
//import android.os.Handler;
//
//import trackwareschoolbus.parentschool.R;
//
///**
// * Created by  on 10/31/2017.
// */
//
//public class RepeateNotificationBroadcastReceiver extends BroadcastReceiver {
//    private boolean mustStop = false;
//    private static android.os.Handler handler;
//    private static MediaPlayer mp;
//    private final int repeatEvery = 1000;
//    private Runnable r = new Runnable() {
//        @Override
//        public void run() {
//
////            if (mustStop) {
////                handler.removeCallbacksAndMessages(null);
////                handler = null;
////            } else {
//            if (mp != null /*& !mp.isPlaying()*/) {
////                mp.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
//                mp.start();
////                }
//
//
//            }
////            if (handler != null)
//                handler.postDelayed(r, repeatEvery);
//        }
//    };
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//
//        if (intent.hasExtra("RepeateNotificationBroadcastReceiver")) {
//            mustStop = intent.getExtras().getBoolean("RepeateNotificationBroadcastReceiver");
//            /**/
//            if (mustStop) {
//
////                if (handler != null) {
//                try{
////                    handler=new android.os.Handler();
//                    handler.removeCallbacksAndMessages(null);
//                    handler = null;
//                }catch (Exception e){
//
//                }
//
////                }
//
//                if (mp != null)
//                    mp.stop();
//            } else {
//                if (handler == null) {
//                    handler = new android.os.Handler();
//                    mp = MediaPlayer.create(context, Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.bus_beb));
//                    mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                    AudioManager audioManager = (AudioManager) context.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
//                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(audioManager.STREAM_MUSIC), 0);
//
//
//                    mp.setVolume(1.0f, 1.0f);
//
////                    mp.setAudioStreamType(AudioManager.STREAM_ALARM);
//                    handler.postDelayed(r, repeatEvery);
//                }
//
//            }
//
//        }
//
//
//    }
//}
