package trackwareschoolbus.parentschool.fragment;

import android.os.Bundle;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class PhotoFragment extends Fragment {


    private int resId;

    public static Fragment newInstance(@DrawableRes int resId) {
        Bundle bundle = new Bundle();
        bundle.putInt("ID", resId);
        PhotoFragment fragment = new PhotoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() == null) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
        resId = getArguments().getInt("ID", -10);
        if (resId == -10) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//        imageView.setImageResource(resId);
        Glide.with(getActivity())
                .load(resId)
                .into(imageView);

        return imageView;
    }
}
