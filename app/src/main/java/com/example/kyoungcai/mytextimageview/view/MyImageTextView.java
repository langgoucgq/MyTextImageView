package com.example.kyoungcai.mytextimageview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kyoungcai.mytextimageview.R;

/**
 * Created by KyoungCai on 2017/7/1.
 */

public class MyImageTextView extends LinearLayout {

    private boolean isH=false;
    private int imgMarginText=0;
    public MyImageTextView(Context context) {
        super(context);
    }

    public MyImageTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyImageTextView);
        ImageView imageView=new ImageView(context);
        TextView textView=new TextView(context);

        float imageSize=0f;
        int textColor=0;
        float textSize=0f;
        int resourceId=-1;
        for (int i = 0; i < typedArray.length(); i++) {
            int attr = typedArray.getIndex(i);
            switch (attr){
                case R.styleable.MyImageTextView_Oriental:
                    this.setGravity(Gravity.CENTER);
                    this.setOrientation(typedArray.getInt(R.styleable.MyImageTextView_Oriental, 0) == 1 ? LinearLayout.HORIZONTAL : LinearLayout.VERTICAL);
                    if(typedArray.getInt(R.styleable.MyImageTextView_Oriental, 0) == 1){
                        isH=true;
                    }
                    break;
                case R.styleable.MyImageTextView_Text:
                    resourceId = typedArray.getResourceId(R.styleable.MyImageTextView_Text, 0);
                    textView.setText(resourceId>0?typedArray.getResources().getText(resourceId):typedArray.getString(R.styleable.MyImageTextView_Text));
                    break;
                case R.styleable.MyImageTextView_Src:
                    resourceId=typedArray.getResourceId(R.styleable.MyImageTextView_Src,0);
                    imageView.setImageResource(resourceId>0?resourceId:R.mipmap.ic_launcher);
                    break;
                case R.styleable.MyImageTextView_TextSize:
                    textSize = typedArray.getDimension(R.styleable.MyImageTextView_TextSize, Utils.sp2px(context, 14f));
                    break;
                case R.styleable.MyImageTextView_TextColor:
                    textColor= typedArray.getColor(R.styleable.MyImageTextView_TextColor, getResources().getColor(R.color.colorPrimary));
                    break;
                case R.styleable.MyImageTextView_ImageSize:
                    imageSize = typedArray.getDimension(R.styleable.MyImageTextView_ImageSize, 0);
                    break;
                case R.styleable.MyImageTextView_ImgTextMargin:
                    imgMarginText = (int) typedArray.getDimension(R.styleable.MyImageTextView_ImgTextMargin, 0);
                    break;
            }
        }
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
        textView.setTextColor(textColor);

        imageView.setLayoutParams(new LayoutParams((int) imageSize, (int) imageSize));

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, (int) imgMarginText,0,0);
        textView.setLayoutParams(layoutParams);

        addView(imageView);
        addView(textView);
        typedArray.recycle();

    }

    public MyImageTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth=0;
        int measuredHeight=0;
        measureChildren(widthMeasureSpec,heightMeasureSpec);

        int widthSpaceSize=MeasureSpec.getSize(widthMeasureSpec);
        int widthSpaceMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightSpaceSize=MeasureSpec.getSize(heightMeasureSpec);
        int heightSpaceMode=MeasureSpec.getMode(heightMeasureSpec);

        //wrapcontent
        if(widthSpaceMode==MeasureSpec.AT_MOST&&heightSpaceMode==MeasureSpec.AT_MOST){
            measuredWidth=Math.max(getChildAt(0).getMeasuredWidth(),getChildAt(1).getMeasuredWidth());
            measuredHeight=getChildAt(0).getMeasuredHeight()+getChildAt(1).getMeasuredHeight()+imgMarginText;
            setMeasuredDimension(measuredWidth,measuredHeight);
        }
        if(widthSpaceMode==MeasureSpec.AT_MOST&&heightSpaceMode==MeasureSpec.AT_MOST&&isH){
            measuredHeight=Math.max(getChildAt(0).getMeasuredHeight(),getChildAt(1).getMeasuredHeight());
            measuredWidth=getChildAt(0).getMeasuredWidth()+getChildAt(1).getMeasuredWidth()+imgMarginText;
            setMeasuredDimension(measuredWidth,measuredHeight);
        }

    }
}
