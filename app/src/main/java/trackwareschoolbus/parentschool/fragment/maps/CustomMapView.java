package trackwareschoolbus.parentschool.fragment.maps;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;

import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;

public class CustomMapView extends MapView {

    private ViewParent mViewParent;

    public CustomMapView(Context context) {
        super(context);
    }

    public CustomMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomMapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setViewParent(@Nullable final ViewParent viewParent) { //any ViewGroup
        mViewParent = viewParent;
    }

    public CustomMapView(Context context, GoogleMapOptions options) {
        super(context, options);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_UP:
//                System.out.println("unlocked");
//                this.getParent().requestDisallowInterceptTouchEvent(false);
//                break;
//            case MotionEvent.ACTION_DOWN:
//                System.out.println("locked");
//                this.getParent().requestDisallowInterceptTouchEvent(true);
//                break;
//        }
//        return super.dispatchTouchEvent(ev);
//    }


    @Override
    public boolean onInterceptTouchEvent(final MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (null == mViewParent) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    mViewParent.requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (null == mViewParent) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else {
                    mViewParent.requestDisallowInterceptTouchEvent(false);
                }
                break;
            default:
                break;
        }

        return super.onInterceptTouchEvent(event);
    }


}

