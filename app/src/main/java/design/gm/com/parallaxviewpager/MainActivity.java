package design.gm.com.parallaxviewpager;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.animation.AccelerateInterpolator;


import java.lang.reflect.Field;

/**
 * Created by gaom on 2016/4/16.
 *
 * Parallax 视察
 * @see ParallaxPageTransformer
 *
 * Rebound Damping 阻尼回弹
 * @see CustomViewPager
 */
public class MainActivity extends FragmentActivity {

    private CustomViewPager viewPager;
    private FixedSpeedScroller mScroller;
    private ParallaxAdapter mAdapter;
    private boolean first;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax);

        viewPager = (CustomViewPager) findViewById(R.id.view_pager);

        ParallaxPageTransformer ppt = new ParallaxPageTransformer(R.id.image, R.id.name);

        viewPager.setPageTransformer(false, ppt);
        mAdapter = new ParallaxAdapter(getSupportFragmentManager());
        mAdapter.setViewPager(viewPager);

        Bundle a = new Bundle();
        a.putInt("image", R.drawable.a);
        a.putString("name", "Beautiful girl! (first)");
        ParallaxFragment aF = new ParallaxFragment();
        aF.setArguments(a);

        Bundle b = new Bundle();
        b.putInt("image", R.drawable.b);
        b.putString("name", "Beautiful girl! (second)");
        ParallaxFragment bF = new ParallaxFragment();
        bF.setArguments(b);

        Bundle c = new Bundle();
        c.putInt("image", R.drawable.c);
        c.putString("name", "Beautiful girl! (last)");
        ParallaxFragment cF = new ParallaxFragment();
        cF.setArguments(c);

        mAdapter.add(aF);
        mAdapter.add(bF);
        mAdapter.add(cF);

        viewPager.setAdapter(mAdapter);
        viewPager.setPagerCount(3);
        viewPager.setCurrentItem(0);

        //改变viewPager滑动速度
        changeViewPagermScroller();

        //初次为用户演示滑动一段距离
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(1);
            }
        }, 1000);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                // 页面被选择是调用
                viewPager.setCurrentIndex(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // 当前Pager的滑动距离
                float currentPosOfPager = arg0 * viewPager.getWidth() + arg2;
                if (currentPosOfPager >= 200 && !first) {//初次为用户演示滑动一段距离
                    first = true;
                    viewPager.setCurrentItem(0);
                    //改变viewPager滑动速度
                    mScroller.setDuration(200);
                }
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });


    }

    /**
     * 用反射改变ViewPager的mScroller属性
     */
    private void changeViewPagermScroller() {
        try {
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            //用自定义的类替换mScroller
            mScroller = new FixedSpeedScroller(viewPager.getContext(),
                    new AccelerateInterpolator(),600);
            mField.set(viewPager, mScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
