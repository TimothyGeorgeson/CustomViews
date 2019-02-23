package com.example.consultants.customviews.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.example.consultants.customviews.R;

import java.util.Random;

public class CustomCircle extends View implements View.OnClickListener{

    private int radius;
    private int fillColor;
    private int cx;
    private int cy;
    private int width;
    private int height;
    private Context context;
    private Paint paint;

    public CustomCircle(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public CustomCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public CustomCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomCircle, defStyleAttr, defStyleRes);
        radius = typedArray.getInt(R.styleable.CustomCircle_radius, 0);
        fillColor = typedArray.getColor(R.styleable.CustomCircle_fillColor, 0);
        paint = new Paint();


        typedArray.recycle();
        setOnClickListener(this);
    }

    private void calculateCircle() {
        cx = width/2;
        cy = height/2;

        int minDim = Math.min(width, height) / 2;
        radius = Math.min(minDim, radius);
        paint.setColor(fillColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(cx, cy, radius, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int desiredHeight = 200;
        int desiredWidth = 200;

        //get mode and value
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


//        Measure width
        if (widthMode == MeasureSpec.EXACTLY) {
//            Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
//            Can't be bigger than that
            width = Math.min(desiredWidth, widthSize);
        } else {
//            whatever we want
            width = desiredWidth;
        }


//        Measure height
        if (heightMode == MeasureSpec.EXACTLY) {
//            Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
//            Can't be bigger than that
            height = Math.min(desiredHeight, heightSize);
        } else {
//            whatever we want
            height = desiredHeight;
        }

        calculateCircle();
        setMeasuredDimension(width, height);
    }

    public void setRadius(int radius) {
        this.radius = radius;
        calculateCircle();
        invalidate();

    }

    private void updateRandomColor() {
        Random rnd = new Random();
        paint.setARGB(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        this.fillColor = paint.getColor();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
//        this.fillColor = context.getColor(R.color.colorPrimary);
        updateRandomColor();
        calculateCircle();
        invalidate();
    }
}
