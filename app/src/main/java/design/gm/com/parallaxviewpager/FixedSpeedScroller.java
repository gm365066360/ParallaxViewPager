package design.gm.com.parallaxviewpager;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;


public class FixedSpeedScroller extends Scroller {

    private int duration = 600;

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public FixedSpeedScroller(Context context, Interpolator interpolator,int duration) {
        super(context, interpolator);
        this.duration = duration;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, this.duration);//注意“this.”
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, duration);
    }

}
