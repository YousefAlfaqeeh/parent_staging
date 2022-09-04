package trackwareschoolbus.parentschool;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.TextView;

import trackwareschoolbus.parentschool.adapters.ViewPagerAdapter;
import trackwareschoolbus.parentschool.basePage.BaseActivity;
import trackwareschoolbus.parentschool.fragment.PhotoFragment;
import trackwareschoolbus.parentschool.utilityParent.UtilityParent;
//import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class TutorialActivity extends BaseActivity {
    private TextView skip_button, end_button, image_indicator;
    private ViewPagerAdapter adapter;
    private ViewPager viewPager;
    private TextView textView1;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**/
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        /**/
        setContentView(R.layout.tutorial_activity);
        setTitle(R.string.title_tutorial);
        /**/
        image_indicator = (TextView) findViewById(R.id.image_indicator);
        textView1 = (TextView) findViewById(R.id.textView1);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
//        viewPager.setPageMargin(-200);
        viewPager.setOffscreenPageLimit(20);
        end_button = (TextView) findViewById(R.id.end_button);
        skip_button = (TextView) findViewById(R.id.skip_button);
        /**/
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        /**/
        addPhotoFragments(
                R.drawable.s1,
                R.drawable.s2,
                R.drawable.s3,
                R.drawable.s4,
                R.drawable.s5,
                R.drawable.s6,
                R.drawable.s7);
        /**/
        viewPager.setAdapter(adapter);
        /**/
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                refreshDots(arg0);
                if (adapter.getCount()-1 == arg0) {
                    end_button.setText(R.string.end_tut);
                    end_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finishThis();
                        }
                    });
                } else {
                    end_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        }
                    });
                    end_button.setText(">");

                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        findViewById(R.id.imageView1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finishThis();
            }
        });
        skip_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finishThis();
            }
        });
        end_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });
        refreshDots(0);

    }

    private void finishThis() {
//        startActivity(new Intent(TutorialActivity.this, MainActivity.class));
        UtilityParent.setStringShared(UtilityParent.TUTORIAL_DONE,"true");
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private void addPhotoFragments(int... imageIds) {
        for (int i = 0; i < imageIds.length; i++) {
            adapter.addFragment(PhotoFragment.newInstance(imageIds[i]), i + "", "fragment" + i);
        }
    }

    private void refreshDots(int selectedPagePosition) {
        image_indicator.setText("");
        for (int i = 0; i < adapter.getCount(); i++) {
            if (selectedPagePosition == i)
                image_indicator.append(createColoredDot(R.color.colorPrimary));
            else
                image_indicator.append(createColoredDot(R.color.white));
        }

    }


    private Spannable createColoredDot(int color) {
        Spannable spannable = new SpannableString(getString(R.string._8226));
        ColorStateList blueColor = new ColorStateList(new int[][]{new int[]{}}, new int[]{this.getResources().getColor(color)});
        TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, 100, blueColor, null);
        spannable.setSpan(highlightSpan, 0, spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }


//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }

    @Override
    public void onBackPressed() {
        FinishAppActivity.finishAppActivity(this);
    }
}
