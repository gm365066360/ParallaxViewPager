package design.gm.com.parallaxviewpager;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

public class ParallaxPageTransformer implements ViewPager.PageTransformer {

    private int id;
    private int is;
    private int border = 10;//pager间距

    public ParallaxPageTransformer(int id, int is) {
        this.id = id;
        this.is = is;
    }

    @Override
    public void transformPage(View view, float position) {

        View parallaxView = view.findViewById(id);
        View textView = view.findViewById(is);

        if (parallaxView != null && Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB ) {
            if (position > -1 && position < 1) {
                float width = parallaxView.getWidth();

                parallaxView.setTranslationX(-(position * width * .5f));
//                textView.setTranslationX(-(position * width ));//文字不动
                textView.setTranslationX(-(position * width *-.2f));
//                Log.e("speed", "" + -(position * width * speed));

                double d = (-(position * width * (-.5f)));

//                if (d>=0&&d<=220) {//文字不动情况
//                    d=d/220;
//                    d = Math.floor(d*100);
//
//                    textView.setAlpha((1 - (float) (d / 100)) );
//                    Log.e("speed", "" + (1 - (float) (d / 100)));
//                }
//
//                if (d>=220) {
//                    d=d/220;
//                    d = Math.floor(d*100);
//
//                    textView.setAlpha((1 - (float) (d / 100)));
////                    Log.e("speed", "" + (1 - (float) (d/100)));
//                }
                if (d>=0&&d<=320) {
                    d=d/320;
                    d = Math.floor(d*100);

                    textView.setAlpha((1 - (float) (d / 100)) );
//                    Log.e("speed", "" + (1 - (float) (d / 100)));
                }

                if (d>=180) {
                    d=d/180;
                    d = Math.floor(d*100);

                    textView.setAlpha((1 - (float) (d / 100)));
//                    Log.e("speed", "" + (1 - (float) (d/100)));
                }
                float sc = ((float)view.getWidth() - border)/ view.getWidth();
                if (position == 0) {
                    view.setScaleX(1);
                    view.setScaleY(1);
                } else {
                    view.setScaleX(sc);
                    view.setScaleY(1);
                }
            }
        }
    }

}