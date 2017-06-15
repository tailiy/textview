package com.example.administrator.textview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/4/20.
 */

public class MyView extends View{
    private Paint mpaint;
    private Path mPath;
    private Bitmap dogbitmap;
    private int dogLeft;
    private int dogTop;
    private Bitmap cachabitmap;
    private Canvas cacheCanvas;
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mpaint = new Paint() ;
        mpaint.setColor(Color.GREEN);
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setStrokeWidth(5);
        mPath = new Path();

        dogbitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        dogbitmap.createBitmap(768,1000,Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas();
        cacheCanvas.setBitmap(cachabitmap);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                    Thread.sleep(10);
                    dogLeft++;
                    dogTop++;
                    postInvalidate();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                float dx = event.getX();
                float dy = event.getY();
                mPath.moveTo(dx,dy);
                break;
            case MotionEvent.ACTION_MOVE:
                float mx = event.getX();
                float my = event.getY();
                mPath.lineTo(mx,my);
                break;
            case MotionEvent.ACTION_UP:
                float ux = event.getX();
                float uy = event.getY();
                mPath.lineTo(ux,uy);
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawColor(Color.RED);

        canvas.drawPath(mPath,mpaint);
        Log.i("myview on draw","aaa");
        super.onDraw(canvas);

    }
}
