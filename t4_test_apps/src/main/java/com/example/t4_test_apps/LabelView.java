package com.example.t4_test_apps;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class LabelView extends View {

    Paint mTextPaint;
    int mTextColor;
    float mTextSize;
    float mTextHeight;
    float mTextWidth;
    int mTextStyle;
    float mTopMargin;
    float mBottomPadding;
    float mTopPadding;
    Paint mTrapezoidPaint;
    int mBackGroundColor;
    float mDegrees;
    String mText = "01";
    int width;
    int height;

    public static final int DEGREES_LEFT = -45;
    public static final int DEGREES_RIGHT = 45;


    public LabelView(Context context) {
        this(context, null);
    }

    public LabelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LabelView);
        // 顶部的外边距
        mTopMargin = ta.getDimension(R.styleable.LabelView_label_top_margin, dp2px(7));
        // 顶部的内边距
        mTopPadding = ta.getDimension(R.styleable.LabelView_label_top_padding, dp2px(3));
        // 底部的内边距
        mBottomPadding = ta.getDimension(R.styleable.LabelView_label_bottom_padding, dp2px(3));
        // 背景色
        mBackGroundColor = ta.getColor(R.styleable.LabelView_label_background_color, Color.parseColor("#66000000"));
        // 文本颜色
        mTextColor = ta.getColor(R.styleable.LabelView_label_text_color, Color.WHITE);
        // 文本字体大小
        mTextSize = ta.getDimension(R.styleable.LabelView_label_text_size, sp2px(11));
        // 文本内容
        mText = ta.getString(R.styleable.LabelView_label_text);
        // 文本样式
        mTextStyle = ta.getInt(R.styleable.LabelView_label_text_style, 2);
        // 旋转角度
        mDegrees = ta.getInt(R.styleable.LabelView_label_direction, 45);

        ta.recycle();

        initTextPaint();
        initTrapezoidPaint();

        resetTextStatus();

    }


    public void setText(String text) {
        mText = text;
        resetTextStatus();
        invalidate();
    }


    public void setBackGroundColor(int color) {
        mTrapezoidPaint.setColor(color);
        invalidate();
    }


    private void initTextPaint() {
        //初始化绘制数字文本的画笔
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(mTextSize);
        if (mTextStyle == 1) {
            mTextPaint.setTypeface(Typeface.SANS_SERIF);
        } else if (mTextStyle == 2) {
            mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        }
    }

    private void initTrapezoidPaint() {
        //初始化绘制梯形背景的画笔
        mTrapezoidPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTrapezoidPaint.setColor(mBackGroundColor);
    }

    private void resetTextStatus() {
        // 测量文本高度
        Rect rectText = new Rect();
        mTextPaint.getTextBounds(mText, 0, mText.length(), rectText);
        mTextWidth = rectText.width();
        mTextHeight = rectText.height();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 1, 位移和旋转canvas
        canvas.translate(0, (float) ((height * Math.sqrt(2)) - height));
        if (mDegrees == DEGREES_LEFT) {
            canvas.rotate(mDegrees, 0, height); // 逆时针旋转, 以(0,height)为旋转点
        } else if (mDegrees == DEGREES_RIGHT) {
            canvas.rotate(mDegrees, width, height); // 顺时针旋转, 以(width,height)为旋转点
        }

        // 2, 绘制梯形背景
        Path path = new Path();
        path.moveTo(0, height);
        path.lineTo(width / 2 - mTopMargin, mTopMargin);
        path.lineTo(width / 2 + mTopMargin, mTopMargin);
        path.lineTo(width, height);
        path.close();
        // 填充路径
        canvas.drawPath(path, mTrapezoidPaint);
        // 绘制文本
        canvas.drawText(mText, width / 2, mTopMargin + mTextHeight + mTopPadding, mTextPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = (int) (mTopMargin + mTopPadding + mBottomPadding + mTextHeight);
        width = 2 * height;
        //控件的真正高度，勾股定理...
        int realHeight = (int) (height * Math.sqrt(2));
        setMeasuredDimension(width, realHeight);
    }

    public int dp2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public float sp2px(float spValue) {
        final float scale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return spValue * scale;
    }
}