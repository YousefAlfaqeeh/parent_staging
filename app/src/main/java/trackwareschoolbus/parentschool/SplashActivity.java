package trackwareschoolbus.parentschool;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import trackwareschoolbus.parentschool.basePage.BaseActivity;

public class SplashActivity extends BaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        try {
//            mFirebaseAnalytics = FirebaseAnalyticsKtxRegistrar.getInstance(this);

        } catch (Exception e) {

        }

        setContentView(R.layout.activity_splash_v2);

//        ImageView splashImageView = findViewById(R.id.splashImageView);
//        Glide.with(splashImageView)
//                .load(splashImageView)
//                .apply(RequestOptions.fitCenterTransform())
////                .transition(DrawableTransitionOptions.withCrossFade())
//                .into(splashImageView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(() -> {

            finish();
            Intent intent = new Intent(this, MainFragmentActivity.class);
//            Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(this,
//                    android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            startActivity(intent);


        }, 3000);
    }

    //    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }
}
