package com.andrzc.platnumber;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by ZC on 2017/11/22.
 */

public class OnKeyboardChangeListener implements ViewTreeObserver.OnGlobalLayoutListener {

    private View mContainer;
    private OnChangeListener mOnChangerListener;

    public OnKeyboardChangeListener(View view, OnChangeListener onChangeListener) {
        mContainer = view;
        mOnChangerListener = onChangeListener;
    }

    @Override
    public void onGlobalLayout() {
        if (mOnChangerListener != null) {
            Rect rect = new Rect();
            mContainer.getWindowVisibleDisplayFrame(rect);
            int heightDifference = mContainer.getRootView().getHeight() - rect.bottom;
            if (heightDifference > 200) {
                mOnChangerListener.onKeyboardShow();
            } else {
                mOnChangerListener.onKeyboardHidden();
            }
        }
    }

    /**
     * 软键盘监听变化接口
     */
    public interface OnChangeListener {
        void onKeyboardShow();//显示接口

        void onKeyboardHidden();//隐藏接口
    }
}