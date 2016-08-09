package com.windy.slideview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by dingfeng on 2016/8/8.
 */
public class SlideViewPager extends FrameLayout {

    private Context mContext;
    private AutoScrollViewPager mAutoScrollViewPager;
    private LinearLayout indicatorRoot;
    private ImageView[] mIndicatorViews;
    private int indicator;
    private int currentItem = 0;
    private int adapterCount = 0;
    private long interval;
    // default interval
    private final static long DEFAULT_INTERVAL_TIME = 3000l;

    public SlideViewPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public SlideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SlideViewPager(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        interval = DEFAULT_INTERVAL_TIME;
        mAutoScrollViewPager = new AutoScrollViewPager(context);
        addView(mAutoScrollViewPager, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        indicatorRoot = new LinearLayout(context);
        LayoutParams p = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        p.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        p.bottomMargin = (int) mContext.getResources().
                getDimension(R.dimen.vp_indicator_root_margin_bottom);
        indicatorRoot.setLayoutParams(p);
        indicator = R.drawable.vp_indicator_selector;
        addView(indicatorRoot);
    }

    /**
     * distance to bottom
     * @param resId
     */
    public void setDistance(int resId) {
        LayoutParams p = (FrameLayout.LayoutParams)indicatorRoot.getLayoutParams();
        p.bottomMargin = (int)getResources().getDimension(resId);
        indicatorRoot.setLayoutParams(p);
        invalidate();
    }

    /**
     * @param size
     */
    public void setIndicator(int size) {
        adapterCount = size;
        mIndicatorViews = new ImageView[size];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        params.leftMargin = (int) mContext.getResources().
                getDimension(R.dimen.vp_indicator_margin_left);
        params.rightMargin = (int) mContext.getResources().
                getDimension(R.dimen.vp_indicator_margin_right);
        for (int i = 0; i < size; i++) {
            mIndicatorViews[i] = new ImageView(mContext);
            mIndicatorViews[i].setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            if (i == 0) {
                mIndicatorViews[i].setBackgroundResource(indicator);
                mIndicatorViews[i].setSelected(true);
            } else {
                mIndicatorViews[i].setBackgroundResource(indicator);
            }
            indicatorRoot.addView(mIndicatorViews[i], params);
        }
    }

    /**
     * @param size
     * @param resId
     */
    public void setIndicator(int size, int resId) {
        adapterCount = size;
        mIndicatorViews = new ImageView[size];
        indicator = resId;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        params.leftMargin = (int) mContext.getResources().getDimension(R.dimen.vp_indicator_margin_left);
        params.rightMargin = (int) mContext.getResources().getDimension(R.dimen.vp_indicator_margin_right);
        for (int i = 0; i < size; i++) {
            mIndicatorViews[i] = new ImageView(mContext);
            mIndicatorViews[i].setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            if (i == 0) {
                mIndicatorViews[i].setBackgroundResource(indicator);
                mIndicatorViews[i].setSelected(true);
            } else {
                mIndicatorViews[i].setBackgroundResource(indicator);
            }
            indicatorRoot.addView(mIndicatorViews[i], params);
        }
    }

    /**
     * @param adapter
     */
    public void setAdapter(PagerAdapter adapter) {
        if (adapter != null) {
            mAutoScrollViewPager.setAdapter(adapter);
            mAutoScrollViewPager.addOnPageChangeListener(onPageChangeListener);
            if (adapterCount > 1) {
                mAutoScrollViewPager.setInterval(interval);
                mAutoScrollViewPager.startAutoScroll();
            }
        }
    }

    /**
     * @param interval
     */
    public void setInterval(long interval) {
        this.interval = interval;
    }

    /**
     * destroy
     */
    public void onDestroy() {
        mAutoScrollViewPager.onDestroy();
        mContext = null;
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            position = position % adapterCount;
            currentItem = position;
            updateIndicator();
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void updateIndicator() {
        for (int i = 0; i < adapterCount; i++) {
            if (currentItem == i) {
                mIndicatorViews[i].setSelected(true);
            } else {
                mIndicatorViews[i].setSelected(false);
            }
        }
    }

}
