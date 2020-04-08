package com.comapny.task1.customViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.comapny.task1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CustomBottomNavigationView extends BottomNavigationView {

    /**
     * the CURVE_CIRCLE_RADIUS represent the radius of the fab button
     */
    private final int CURVE_CIRCLE_RADIUS = 90;
    private Path mPath;
    private Paint mPaint;
    // the coordinates of the first curve
    private Point mFirstCurveStartPoint = new Point();
    private Point mFirstCurveEndPoint = new Point();
    private Point mFirstCurveControlPoint1 = new Point();
    private Point mFirstCurveControlPoint2 = new Point();

    //the coordinates of the second curve
    @SuppressWarnings("FieldCanBeLocal")
    private Point mSecondCurveStartPoint = new Point();
    private Point mSecondCurveEndPoint = new Point();
    private Point mSecondCurveControlPoint1 = new Point();
    private Point mSecondCurveControlPoint2 = new Point();
    private int mNavigationBarWidth;
    private int mNavigationBarHeight;

    public CustomBottomNavigationView(Context context) {
        super(context);
        init();
    }

    public CustomBottomNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomBottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.white));
        setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // get width and height of navigation bar
        // Navigation bar bounds (width & height)
        mNavigationBarWidth = getWidth();
        mNavigationBarHeight = getHeight();
        // the coordinates (x,y) of the start point before curve
        mFirstCurveStartPoint.set((mNavigationBarWidth / 2) - (CURVE_CIRCLE_RADIUS * 2) - (CURVE_CIRCLE_RADIUS / 3), 0);
        // the coordinates (x,y) of the end point after curve
        mFirstCurveEndPoint.set(mNavigationBarWidth / 2, CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 2)+10);
        // same thing for the second curve
        mSecondCurveStartPoint = mFirstCurveEndPoint;
        mSecondCurveEndPoint.set((mNavigationBarWidth / 2) + (CURVE_CIRCLE_RADIUS * 2) + (CURVE_CIRCLE_RADIUS / 3), 0);

        // the coordinates (x,y)  of the 1st control point on a cubic curve
        mFirstCurveControlPoint1.set(mFirstCurveStartPoint.x + CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4)-((CURVE_CIRCLE_RADIUS / 4)), mFirstCurveStartPoint.y+(CURVE_CIRCLE_RADIUS ));
        // the coordinates (x,y)  of the 2nd control point on a cubic curve
        mFirstCurveControlPoint2.set(mFirstCurveEndPoint.x - (CURVE_CIRCLE_RADIUS * 2) + CURVE_CIRCLE_RADIUS, mFirstCurveEndPoint.y);

        mSecondCurveControlPoint1.set(mSecondCurveStartPoint.x + (CURVE_CIRCLE_RADIUS * 2) - CURVE_CIRCLE_RADIUS, mSecondCurveStartPoint.y);
        mSecondCurveControlPoint2.set(mSecondCurveEndPoint.x - (CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4))+((CURVE_CIRCLE_RADIUS / 4)), mSecondCurveEndPoint.y+(CURVE_CIRCLE_RADIUS ));

        Log.i(TAG, "onSizeChanged: height: "+mNavigationBarHeight);
        Log.i(TAG, "onSizeChanged: width: "+mNavigationBarWidth);
        Log.i(TAG, "onSizeChanged: lineTo: "+mFirstCurveControlPoint1.x+","+mFirstCurveStartPoint.y);

        Log.i(TAG, "onSizeChanged: 1cubeTo1: "+mFirstCurveControlPoint1.x+","+mFirstCurveControlPoint1.y);
        Log.i(TAG, "onSizeChanged: 1cubeTo2: "+mFirstCurveControlPoint2.x+","+mFirstCurveControlPoint2.y);
        Log.i(TAG, "onSizeChanged: 1cubeTo3: "+mFirstCurveEndPoint.x+","+mFirstCurveEndPoint.y);


        Log.i(TAG, "onSizeChanged: 2cubeTo1: "+mSecondCurveControlPoint1.x+","+mSecondCurveControlPoint1.y);
        Log.i(TAG, "onSizeChanged: 2cubeTo2: "+mSecondCurveControlPoint2.x+","+mSecondCurveControlPoint2.y);
        Log.i(TAG, "onSizeChanged: 2cubeTo3: "+mSecondCurveControlPoint2.x+","+mSecondCurveEndPoint.y);


        Log.i(TAG, "onSizeChanged: lineTo: "+mNavigationBarWidth+","+"0");
        Log.i(TAG, "onSizeChanged: lineTo: "+mNavigationBarWidth+","+mNavigationBarHeight);
        Log.i(TAG, "onSizeChanged: lineTo: "+"0"+","+mNavigationBarHeight);



        mPath.reset();
        mPath.moveTo(0, 0);
        mPath.lineTo(mFirstCurveControlPoint1.x+(CURVE_CIRCLE_RADIUS/6), mFirstCurveStartPoint.y);
        mPath.cubicTo(mFirstCurveControlPoint1.x, mFirstCurveControlPoint1.y,
                mFirstCurveControlPoint2.x+(CURVE_CIRCLE_RADIUS/6), mFirstCurveControlPoint2.y,
                mFirstCurveEndPoint.x, mFirstCurveEndPoint.y);

        mPath.cubicTo(mSecondCurveControlPoint1.x-(CURVE_CIRCLE_RADIUS/6), mSecondCurveControlPoint1.y,
                mSecondCurveControlPoint2.x, mSecondCurveControlPoint2.y,
                mSecondCurveControlPoint2.x-(CURVE_CIRCLE_RADIUS/6), mSecondCurveEndPoint.y);

        mPath.lineTo(mNavigationBarWidth, 0);
        mPath.lineTo(mNavigationBarWidth, mNavigationBarHeight);
        mPath.lineTo(0, mNavigationBarHeight);
        mPath.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }
}