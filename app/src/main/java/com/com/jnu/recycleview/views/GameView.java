package com.com.jnu.recycleview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;
import java.util.ArrayList;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {//由callback来负责画图

    private float touchedX;//点击的x坐标
    private float touchedY;//点击的y坐标
    private boolean isTouched=false;//标志是否点击

    //重载4个构造函数
    public GameView(Context context) {
        super(context);
        initView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }
    //重载4个构造函数

    @Override
    public boolean onTouchEvent(MotionEvent event) {//记录鼠标点击的坐标
        if(MotionEvent.ACTION_UP==event.getAction())
        {
            touchedX = event.getRawX();
            touchedY = event.getRawY();
            isTouched = true;
        }
        return true;
    }

    private void initView()//初始化SurfaceView
    {
        surfaceHolder=getHolder();
        surfaceHolder.addCallback(this);//注册回调办法
    }

    private SurfaceHolder surfaceHolder;
    private DrawThread drawThread=null;
    private ArrayList<Spriter> spriterArrayList=new ArrayList<>();//精灵数组
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {//创建线程
        if(spriterArrayList.size()==0) {
            for (int i = 0; i < 5; ++i) {
                Spriter spriter = new Spriter(this.getContext());
                spriter.setX(i * 50);
                spriter.setY(i * 50);
                spriter.setDirection((float) (Math.random() * 2 * Math.PI));//对精灵初始化
                spriterArrayList.add(spriter);
            }
        }
        drawThread=new DrawThread();
        drawThread.start();//开启子线程
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    //窗口大小变化时需要处理的
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        drawThread.stopThread();//终止画图线程
    }

    class DrawThread extends Thread {//定义一个子线程DrawThread负责绘图
        private boolean isDrawing=true;//子线程标志

        public void stopThread()//停止
        {
            isDrawing=false;//跳出死循环

            try {
                join();//停止
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {//run负责定时绘图刷新，绘图完后会停止
            int hitCount=0;//记录点击的次数
            while(isDrawing)//进行一个死循环，主线程在里面不断绘图
            {
                Canvas canvas =null;//canvas画布
                try {
                    canvas = surfaceHolder.lockCanvas();//获得canvas对象
                    canvas.drawColor(Color.GRAY);//设置背景颜色

                    if(isTouched) {//鼠标已经点击
                        float tempX = touchedX;//使用临时变量保存鼠标点击的坐标
                        float tempY = touchedY;

                        isTouched = false;//使本次点击失效，下一次再次点击才变成true
                        for (Spriter spriter : spriterArrayList) {
                            if(spriter.isTouched(tempX, tempY)) {//判断是否点击到地鼠
                                hitCount++;
                                spriterArrayList.remove(spriter);//删除已经点击的地鼠，最好用一个list暂存起来，集中删除
                            }
                        }
                    }
                    Paint textPaint = new Paint();
                    textPaint.setColor(Color.BLACK);
                    textPaint.setTextSize(50);
                    canvas.drawText("you hit "+hitCount+" hamsters"+" , and you get "+hitCount*5+" scores.",50,100,textPaint);//记录打了多少只地鼠

                    for (Spriter spriter: spriterArrayList) {//for each
                        spriter.move(canvas.getHeight(), canvas.getWidth());//精灵移动
                    }
                    for (Spriter spriter: spriterArrayList) {
                        spriter.draw(canvas);//精灵画图
                    }
                }
                catch(Exception e) {

                }
                finally {
                    if(null!=canvas)surfaceHolder.unlockCanvasAndPost(canvas);//释放canvas对象并提交画布进行刷新
                }

                try {
                    sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //drawing绘图工作
            }
        }
    }
}
