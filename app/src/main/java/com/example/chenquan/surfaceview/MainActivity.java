package com.example.chenquan.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Surfaceview surfaceview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new Drawricle(this));
//        setContentView(R.layout.activity_main);
        setContentView(new Surfaceview(this));
    }
    public class Drawricle extends View{
        float r = 10;
        Paint paint;
        public Drawricle(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.STROKE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.translate(500,200);//绘制点的其实坐标
            canvas.drawCircle(-100,0,r++,paint);//前两个参数是用来控制圆心的坐标相对于绘制点起始位置的位置
            if (r>100){
                r=10;
            }
            invalidate();
        }
    }
}
