package com.example.chenquan.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by chenquan on 2017/1/13.
 * SurfaceView可以理解成是一个画板
 * Canvas可以理解成是画布
 * Paint可以理解成是画笔
 */

public class Surfaceview extends SurfaceView implements SurfaceHolder.Callback{

    private  LoopThread loopThread;

    public Surfaceview(Context context){
        super(context);
        init();
    }
    private void init(){
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        loopThread = new LoopThread(holder,getContext());

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.v("surfaceCreated:::","");
        loopThread.isRunning = true;
        loopThread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.v("surfaceChanged:::","");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.v("surfaceDestroyed:::","");
        loopThread.isRunning = false;
        try {
            loopThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class LoopThread extends Thread{
        SurfaceHolder surfaceHolder ;
        Context context ;
        boolean isRunning;
        float r = 10f;
        Paint paint;
        public LoopThread(SurfaceHolder surfaceHolder,Context context){
            this.context = context;
            this.surfaceHolder = surfaceHolder;
            isRunning = false;
            //画笔
            paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.STROKE);

        }

        @Override
        public void run() {
            Canvas canvas = null;
            while (isRunning){
                try {
                synchronized (surfaceHolder) {
                    //可以理解成画布
                    canvas = surfaceHolder.lockCanvas();
                    if (canvas!=null) {
                        doDraw(canvas);
                    }
                    //一秒钟100次  帧数：100
                    Thread.sleep(10);
                        }
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    } finally {
                    if (canvas!=null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    }

                }
            }

        private void doDraw(Canvas canvas){
            //重新刷新画布，不做这一步的话上一步的操作会留下痕迹
            canvas.drawColor(Color.WHITE);
            canvas.translate(200,200);
            canvas.drawCircle(0,0,r++,paint);
            if (r>100){
                r=10;
            }
        }
    }



}
