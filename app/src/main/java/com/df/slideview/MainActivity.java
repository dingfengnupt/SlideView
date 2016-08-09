package com.df.slideview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.windy.slideview.SlideViewPager;

public class MainActivity extends AppCompatActivity {

    private static String[] mUrls = new String[] {
            "http://pic.pptbz.com/pptpic/201110/20111014111307895.jpg",
            "http://pic1.desk.chinaz.com/file/11.07.10/9/fengjingbz20.jpg",
            "http://www.deskcar.com/desktop/fengjing/2011624235723/15.jpg",
            "http://f.hiphotos.baidu.com/zhidao/pic/item/7acb0a46f21fbe09c6ac6ed16b600c338644ad01.jpg",
            "http://hiphotos.baidu.com/%D3%E3%B6%F9%BE%F8%C0%E1/pic/item/78ff6ad76679871b07088bd5.jpg"
    };

    private SlideViewPager mSlideViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSlideViewPager = (SlideViewPager) findViewById(R.id.slideViewPager);
//        mSlideViewPager.setDistance(R.dimen.indicator_root_margin_bottom);
//        mSlideViewPager.setIndicator(mUrls.length, R.drawable.indicator);
        mSlideViewPager.setIndicator(mUrls.length);
        mSlideViewPager.setInterval(5000);
        MyAdapter adapter = new MyAdapter(this, mUrls);
        adapter.setInfiniteLoop(true);
        mSlideViewPager.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSlideViewPager.onDestroy();
    }
}
