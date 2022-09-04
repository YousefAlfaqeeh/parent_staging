package trackwareschoolbus.parentschool.toolsV2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.interfaceParent.OnActionDoneListener;


public class ViewToolsV2 {


    public static View inflaterView(Context c, int layoutId) {
        return LayoutInflater.from(c).inflate(layoutId, null);
    }


    public static void convertViewToBitmap(View view, OnActionDoneListener<Bitmap> onBitmapReady) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
                final Bitmap clusterBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(clusterBitmap);
                view.draw(canvas);
                new android.os.Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        onBitmapReady.OnActionDone(clusterBitmap);
                    }
                });

            }
        }).start();

    }

//    public static void convertViewToBitmapDescriptor(Context c, int viewId, OnActionDoneListener<BitmapDescriptor> onBitmapReady) {
//        convertViewToBitmapDescriptor(inflaterView(c, viewId), onBitmapReady);
//    }


    public static void convertViewToBitmapDescriptor(View view, OnActionDoneListener<BitmapDescriptor> onBitmapReady) {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

                view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
                final Bitmap clusterBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(clusterBitmap);
                view.draw(canvas);
                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(clusterBitmap);
                new android.os.Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        onBitmapReady.OnActionDone(bitmapDescriptor);
                    }
                });

            }
        }).start();

    }

    private static BitmapDescriptor getMarkerIconFromDrawable(Drawable drawable) {
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


    public static void hideKeyboard(Activity activity) {
        try {
            View view = activity.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {

        }
    }


    public static void shakeWithMessage(View v, int messageId) {
        v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.shake_shake));
        Toast.makeText(v.getContext(), messageId, Toast.LENGTH_SHORT).show();

    }

    public static void shakeThis(View... v) {
        for (int i = 0; i < v.length; i++) {
            v[i].startAnimation(AnimationUtils.loadAnimation(v[i].getContext(), R.anim.shake_shake));
        }
    }


    public static boolean isStillEmpty(TextView... v) {
       return isStillEmpty(false, false, v);
    }

    public static boolean isStillEmpty(boolean withShake, boolean withErrorMessage, TextView... v) {

        for (int i = 0; i < v.length; i++) {
            final TextView currentV = v[i];
            if (currentV == null) {
                continue;
            }
            if (!currentV.isShown()) {
                continue;
            }
//            if (v[i] instanceof EditText) {
            if (currentV.getText().toString().trim().equals("")) {
                if (withShake)
                    shakeThis(currentV);
                if (withErrorMessage) {
                    try {
                        if (currentV.getTag() != null && currentV.getTag() instanceof Integer) {
                            int stringId = (int) currentV.getTag();
                            SnackbarToolsV2.show(currentV, stringId);
                        } else if (currentV.getTag() != null && currentV.getTag() instanceof String) {
                            String string = (String) currentV.getTag();
                            SnackbarToolsV2.show(currentV, string);
                        }
                    } catch (Exception e) {

                    }


//                    new SimpleTooltip.Builder(currentV.getContext())
//                            .anchorView(currentV)
//                            .text(currentV.getContext().getString(R.string.this_field_is_required))
//                            .gravity(Gravity.CENTER)
//                            .animated(true)
////                            .transparentOverlay(false)
//                            .build()
//                            .show();

//                    EasyPopup mCirclePop;

//                    mCirclePop = new EasyPopup(currentV.getContext())
////                            .setContentView(R.layout.layout_circle_comment)
//                            .setAnimationStyle(R.style.CirclePopAnim)
//                            //是否允许点击PopupWindow之外的地方消失
//                            .setFocusAndOutsideEnable(true)
//                            .createPopup();

//                    currentV.setError(currentV.getContext().getString(R.string.this_field_is_required),null);
//                    currentV.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            currentV.setError(null);
//                        }
//                    }, 2000);
                }


                return true;
            }
//            }

//            else if (v[i] instanceof Button) {
//                final Button button = (Button) v[i];
//                if (button.getText().toString().trim().equals("")) {
//                    if (withShake)
//                        shakeThis(button);
//                    if (withErrorMessage) {
//                        button.setError(button.getContext().getString(R.string.this_field_is_required));
//                        button.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                button.setError(null);
//                            }
//                        }, 700);
//                    }
//
//
//                }
//            }


        }
        return false;
    }

    public static int dp2px(Context c, final float dpValue) {
        final float scale = c.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
