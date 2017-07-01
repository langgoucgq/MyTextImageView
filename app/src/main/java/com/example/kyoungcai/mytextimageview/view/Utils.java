package com.example.kyoungcai.mytextimageview.view;

import android.content.Context;

/**
 * Created by KyoungCai on 2017/7/1.
 */

public class Utils {

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
