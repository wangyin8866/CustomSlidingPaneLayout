package com.wy.slidingpanelayoutdemo;

import android.content.Context;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by wy on 2016/8/17.
 * 处理嵌套viewpager滑动冲突
 */
public class CustomSliding extends SlidingPaneLayout {
    private float mInitialMotionX;
    private float mInitialMotionY;
    private float mEdgeSlop;

    public CustomSliding(Context context) {
        this(context, null);
    }

    public CustomSliding(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSliding(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        ViewConfiguration config = ViewConfiguration.get(context);
        mEdgeSlop = config.getScaledEdgeSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mInitialMotionX = ev.getX();
                mInitialMotionY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float x = ev.getX();
                final float y = ev.getY();
                if (mInitialMotionX > mEdgeSlop && !isOpen() && canScroll(this, false,
                        Math.round(x - mInitialMotionX), Math.round(x), Math.round(y))) {

                    // How do we set super.mIsUnableToDrag = true?

                    // send the parent a cancel event
                    MotionEvent cancelEvent = MotionEvent.obtain(ev);
                    cancelEvent.setAction(MotionEvent.ACTION_CANCEL);
                    return super.onInterceptTouchEvent(cancelEvent);

                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
