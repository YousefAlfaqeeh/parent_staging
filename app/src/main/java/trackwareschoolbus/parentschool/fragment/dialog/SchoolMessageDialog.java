//package trackwareschoolbus.parentschool.fragment.dialog;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.widget.AppCompatImageView;
//import android.text.Spanned;
//import android.view.View;
//import android.view.Window;
//import android.widget.TextView;
//
//import trackwareschoolbus.parentschool.R;
//import trackwareschoolbus.parentschool.basePage.BaseDialog;
//
///**
// * Created by  on 3/9/2017.
// */
//
//
//public class SchoolMessageDialog extends BaseDialog {
//
//
//    private TextView message_title, message_content;
//    private View imgClose;
//    private AppCompatImageView main_image;
//
//
//
//    public SchoolMessageDialog(@NonNull Context context, String title, String message) {
//        super(context);
//
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setBackgroundDrawableResource(R.color.transparent);
//        setCancelable(false);
//        setContentView(R.layout.dialog_school_message);
//        imgClose = findViewById(R.id.imgClose);
//        message_title = (TextView) findViewById(R.id.message_title);
//        main_image = (AppCompatImageView) findViewById(R.id.main_image);
//        message_content = (TextView) findViewById(R.id.message_content);
//        if (title.equals(context.getString(R.string.notification))) {
//            ((AppCompatImageView) findViewById(R.id.main_image)).setImageResource(R.drawable.img_notification);
//            ((AppCompatImageView) findViewById(R.id.main_image)).setColorFilter(ContextCompat.getColor(context, R.color.school_message_color), android.graphics.PorterDuff.Mode.MULTIPLY);
//
//        }
//        imgClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
//
//        message_title.setText(title);
//        if (message == null)
//            message_content.setVisibility(View.GONE);
//        else
//            message_content.setText(message);
//
//
//    }
//
//
//    public SchoolMessageDialog setMainImage(int imageId) {
//        main_image.setImageResource(imageId);
//        return this;
//    }
//    //    public SchoolMessageDialog loadImageFromURL(String imageUrl) {
////        Glide.with(context).load(imageUrl).fitCenter().into(dialoge_image);
////        return this;
////    }
//
//}
