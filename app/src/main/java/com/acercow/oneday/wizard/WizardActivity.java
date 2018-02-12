package com.acercow.oneday.wizard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acercow.androidlib.activity.BaseActivity;
import com.acercow.oneday.R;
import com.acercow.oneday.note.HomeActivity;
import com.acercow.oneday.utils.ActivityUtils;

public class WizardActivity extends BaseActivity {
    private int[] colors = {
            R.color.colorPrimaryDark,
            R.color.colorWizard1,
            R.color.colorPrimaryDark2,
            R.color.colorWizard2
    };
    private int[] wizardImages;
    private String[] wizardTitles;
    private String[] wizardDescriptions;

    private Button btnSkip;
    private Button btnPagerGotIt;
    private ImageView ivPagerWizard;
    private TextView tvPagerTitle;
    private TextView tvPagerDescripiton;
    private LinearLayout lytDotContainer;
    private RelativeLayout lytPagerParent;
    private ViewPager vpWizard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_wizard;
    }

    @Override
    public void initView(View view) {
        btnSkip = findViewById(R.id.skip_wizard_button);
        lytDotContainer = findViewById(R.id.dot_wizard_container);
        vpWizard = findViewById(R.id.wizard_viewpager);
    }

    @Override
    public void doBusiness(Context mContext) {

        TypedArray typedArray = getResources().obtainTypedArray(R.array.wizard_images);
        wizardImages = new int[typedArray.length()];
        for (int i = 0; i < typedArray.length(); i++) {
            wizardImages[i] = typedArray.getResourceId(i, 0);
        }
        wizardTitles = getResources().getStringArray(R.array.wizard_titles);
        wizardDescriptions = getResources().getStringArray(R.array.wizard_descriptions);
        WizardPagerAdapter adapter = new WizardPagerAdapter();
        vpWizard.setAdapter(adapter);
        vpWizard.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateDotView(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        updateDotView(0);
        btnSkip.setOnClickListener(v -> {
            ActivityUtils.startActivity(this, HomeActivity.class);
            finish();
        });
    }

    @Override
    public void onSingleClick(View v) {

    }

    private void updateDotView(int position) {
        lytDotContainer.removeAllViews();
        ImageView[] ivDots = new ImageView[wizardTitles.length];
        for (int i = 0; i < wizardTitles.length; i++) {
            ivDots[i] = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(15, 15);
            layoutParams.setMargins(10, 10, 10, 10);
            ivDots[i].setLayoutParams(layoutParams);
            ivDots[i].setImageResource(R.drawable.shape_circle_white);
            ivDots[i].setColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_IN);
            lytDotContainer.addView(ivDots[i]);
        }
        ivDots[position].setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
    }

    class WizardPagerAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View pager = getLayoutInflater().inflate(R.layout.wizard_pager, container, false);
            ivPagerWizard = pager.findViewById(R.id.wizard_image);
            tvPagerTitle = pager.findViewById(R.id.wizard_title);
            tvPagerDescripiton = pager.findViewById(R.id.wizard_description);
            lytPagerParent = pager.findViewById(R.id.wizard_parent);
            btnPagerGotIt = pager.findViewById(R.id.got_it_wizard_button);
            btnPagerGotIt.setOnClickListener(v -> {
                ActivityUtils.startActivity(WizardActivity.this, HomeActivity.class);
                finish();
            });
            ivPagerWizard.setImageResource(wizardImages[position]);
            tvPagerTitle.setText(wizardTitles[position]);
            tvPagerDescripiton.setText(wizardDescriptions[position]);
            lytPagerParent.setBackgroundColor(getResources().getColor(colors[position]));
            container.addView(pager);
            if (position ==wizardTitles.length - 1) {
                btnPagerGotIt.setVisibility(View.VISIBLE);
            } else {
                btnPagerGotIt.setVisibility(View.GONE);
            }
            return pager;
        }

        @Override
        public int getCount() {
            return wizardTitles.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
