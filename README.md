#SlideView
广告轮播和指示点
https://github.com/dingfengnupt/SlideView

#dependency

Step 1. Add it in your root build.gradle at the end of repositories:
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}

Step 2. Add the dependency
dependencies {
    compile 'com.github.dingfengnupt:SlideView:1.0.3'
}

#use
    mSlideViewPager = (SlideViewPager) findViewById(R.id.slideViewPager);
    mSlideViewPager.setIndicator(mUrls.length);
    mSlideViewPager.setInterval(5000);
    MyAdapter adapter = new MyAdapter(this, mUrls);
    adapter.setInfiniteLoop(true);
    mSlideViewPager.setAdapter(adapter);

#custom indicator：
mSlideViewPager.setIndicator(mUrls.length, R.drawable.indicator);

indicator xml：
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/selected" android:state_selected="true" />
    <item android:drawable="@drawable/unselected" />
</selector>