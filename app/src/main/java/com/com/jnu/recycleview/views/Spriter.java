package com.com.jnu.recycleview.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import com.com.jnu.recycleview.R;

public class Spriter {//精灵类就是随事件变化的图片
    float x, y;//当前坐标
    int drawableResourceId;//图片
    float direction;//运动方向
    Context context;

    public Spriter(Context context)//构造函数
    {
        this.context = context;
    }

    public void move(float maxHeight, float maxWidth)//移动，两个参数控制边界
    {
        if (Math.random() < 0.05) {//在每次移动时有5%的概率改变方向
            setDirection((float) (Math.random() * 2 * Math.PI));
        }
        x = (float) (x + 30 * Math.cos(direction));
        y = (float) (y + 30 * Math.sin(direction));
        //防止跑出屏幕后不见了
        if (x < 0) x += maxWidth;
        if (x > maxWidth) x -= maxWidth;
        if (y < 0) y += maxHeight;
        if (y > maxHeight) y -= maxHeight;
    }

    public void draw(Canvas canvas)//画图
    {
        Bitmap bitmap = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.dishu)).getBitmap();//设置图片
        Paint mBitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//画笔
        mBitPaint.setFilterBitmap(true);
        mBitPaint.setDither(true);
        canvas.drawBitmap(bitmap, getX(), getY(), mBitPaint);//画图
    }

    //下面是get和set函数
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    //public int getDrawableResourceId() {return drawableResourceId;}

    //public void setDrawableResourceId(int drawableResourceId) {this.drawableResourceId = drawableResourceId;}

    //public float getDirection() {return direction;}

    public void setDirection(float direction) {
        this.direction = direction;
    }

    public boolean isTouched(float touchedX, float touchedY) {
        return (touchedX-x)*(touchedX-x)+(touchedY-y)*(touchedY-y)<=30000;//判断是否点击到
    }
}