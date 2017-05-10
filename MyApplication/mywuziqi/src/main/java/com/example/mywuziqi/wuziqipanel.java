package com.example.mywuziqi;

import android.os.Bundle;
import android.os.Parcelable;
import android.provider.CalendarContract;
import android.view.View;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class wuziqipanel extends View {

    private int mPanelwidth;
    private float mLineheight;
    int MAX_LINE = 10;
    private int MAX_COUNT_LINE = 5;
    private Paint mPaint = new Paint();
    private Bitmap mWhitePiece; //白棋子
    private Bitmap mBlockPiece; //黑棋子
    private float radioPieceOfLineheight = 3 * 1.0f / 4; //限制棋子的大小，让其不超过棋盘

    private boolean mIswhite = true; //白棋先手，或者说轮到白棋子
    private ArrayList<Point> mwhiteArray = new ArrayList<>();
    private ArrayList<Point> mblackArray = new ArrayList<>();
    private boolean misGameover;
    private boolean miswhitewinner;


    private Paint mpaint = new Paint();

    public wuziqipanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        //setBackgroundColor(0x44ff0000);
        init();
    }

    private void init() {
        mpaint.setColor(0x88000000);//灰色，半透明
        mpaint.setAntiAlias(true);
        mpaint.setDither(true);
        mpaint.setStyle(Paint.Style.STROKE);
        mWhitePiece = BitmapFactory.decodeResource(getResources(), R.drawable.stone_w2);
        mBlockPiece = BitmapFactory.decodeResource(getResources(), R.drawable.stone_b1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = Math.min(widthSize, heightSize);

        if (widthMode == MeasureSpec.UNSPECIFIED) {
            width = heightSize;
        } else if (heightMode == MeasureSpec.UNSPECIFIED) {
            width = widthSize;
        }
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {//尺寸相关的变量进行限制
        super.onSizeChanged(w, h, oldw, oldh);
        mPanelwidth = w;
        mLineheight = mPanelwidth * 1.0f / MAX_LINE;
        int pieceWidth = (int) (mLineheight * radioPieceOfLineheight);
        mWhitePiece = Bitmap.createScaledBitmap(mWhitePiece, pieceWidth, pieceWidth, false);
        mBlockPiece = Bitmap.createScaledBitmap(mBlockPiece, pieceWidth, pieceWidth, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBoard(canvas);
        drawpieces(canvas);
        checkGameover();
    }

    private void checkGameover() {
        boolean whitewin = checkfiveinline(mwhiteArray);
        boolean blackwin = checkfiveinline(mblackArray);

        if (whitewin || blackwin) {
            misGameover = true;
            miswhitewinner = whitewin;

            String text = miswhitewinner ? "白棋胜利" : "黑棋胜利";
            Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
        }

    }
    private boolean checkfiveinline(List<Point> points) {
        for (Point p : points) {
            int x = p.x;
            int y = p.y;
            boolean win = checkhorizontal(x, y, points);
            if (win) return true;
            win = checkvetical(x, y, points);
            if (win) return true;
            win = checkleft(x, y, points);
            if (win) return true;
            win = checkright(x, y, points);
            if (win) return true;
        }
        return false;
    }

    private boolean checkhorizontal(int x, int y, List<Point> points) { //判断棋子横向是否有五个棋子相邻
        int count = 1;
        for (int i = 1; i < MAX_COUNT_LINE; i++) {
            if (points.contains(new Point(x - i, y))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_COUNT_LINE) return true;
        for (int i = 1; i < MAX_COUNT_LINE; i++) {
            if (points.contains(new Point(x + i, y))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_COUNT_LINE) return true;
        return false;
    }

    private boolean checkvetical(int x, int y, List<Point> points) { //判断棋子纵向是否有五个棋子相邻
        int count = 1;
        for (int i = 1; i < MAX_COUNT_LINE; i++) {
            if (points.contains(new Point(x, y - i))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_COUNT_LINE) return true;
        for (int i = 1; i < MAX_COUNT_LINE; i++) {
            if (points.contains(new Point(x, y + i))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_COUNT_LINE) return true;
        return false;
    }

    private boolean checkleft(int x, int y, List<Point> points) { //判断棋子左斜是否有五个棋子相邻
        int count = 1;
        for (int i = 1; i < MAX_COUNT_LINE; i++) {
            if (points.contains(new Point(x - i, y + i))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_COUNT_LINE) return true;
        for (int i = 1; i < MAX_COUNT_LINE; i++) {
            if (points.contains(new Point(x - i, y - i))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_COUNT_LINE) return true;
        return false;
    }

    private boolean checkright(int x, int y, List<Point> points) { //判断棋子右斜是否有五个棋子相邻
        int count = 1;
        for (int i = 1; i < MAX_COUNT_LINE; i++) {
            if (points.contains(new Point(x + i, y - i))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_COUNT_LINE) return true;
        for (int i = 1; i < MAX_COUNT_LINE; i++) {
            if (points.contains(new Point(x + i, y + i))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_COUNT_LINE) return true;
        return false;
    }

    private void drawpieces(Canvas canvas) {  //绘制棋子的方法
        for (int i = 0, n = mwhiteArray.size(); i < n; i++) {
            Point whitepoint = mwhiteArray.get(i);
            canvas.drawBitmap(mWhitePiece,
                    (whitepoint.x + (1 - radioPieceOfLineheight) / 2) * mLineheight,
                    (whitepoint.y + (1 - radioPieceOfLineheight) / 2) * mLineheight, null);
        }
        for (int i = 0, n = mblackArray.size(); i < n; i++) {
            Point blackpoint = mblackArray.get(i);
            canvas.drawBitmap(mBlockPiece,
                    (blackpoint.x + (1 - radioPieceOfLineheight) / 2) * mLineheight,
                    (blackpoint.y + (1 - radioPieceOfLineheight) / 2) * mLineheight, null);
        }
    }
    private void drawBoard(Canvas canvas) {                 //绘制棋盘的方法

        int w = mPanelwidth;
        float lineHeigt = mLineheight;

        for (int i = 0; i < MAX_LINE; i++) {
            int startX = (int) (lineHeigt / 2);
            int endX = (int) (w - lineHeigt / 2);

            int y = (int) ((0.5 + i) * lineHeigt);

            canvas.drawLine(startX, y, endX, y, mpaint);
            canvas.drawLine(y, startX, y, endX, mpaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (misGameover) {
            return false;
        }

        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            Point p = getValidPoint(x, y);
            if (mwhiteArray.contains(p) || mblackArray.contains(p)) {
                return false;
            }
            if (mIswhite) {
                mwhiteArray.add(p);
            } else {
                mblackArray.add(p);
            }
            invalidate();
            mIswhite = !mIswhite;
        }
        return true;
    }
    private Point getValidPoint(int x, int y) {
        return new Point((int) (x / mLineheight), (int) (y / mLineheight));
    }

    public void restrat() {

        mwhiteArray.clear();
        mblackArray.clear();
        misGameover = false;
        miswhitewinner = false;
        invalidate();
    }

    public void Edit() {
        System.exit(0);
    }
}

    /*private static final String INSTANCE = "instance";
    private static final  String INSTANCE_GAMEOVER ="instance_gameover";
    private static final  String INSTANCE_WHITE_ARRAY ="instance_white_array";
    private static final  String INSTANCE_BLACK_ARRAY="instance_black_array";
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle =new Bundle();
        bundle.putParcelableArray(INSTANCE, super.onSaveInstanceState());
        bundle.putBoolean(INSTANCE_GAMEOVER,misGameover);
        bundle.putParcelableArray(INSTANCE_WHITE_ARRAY,mwhiteArray);
        bundle.putParcelableArray(INSTANCE_BLACK_ARRAY,mblackArray);
        return bundle;
    }
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }*/


