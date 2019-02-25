package com.ssl.common.mvp.base;

import java.lang.ref.WeakReference;

public class BasePresenter<T extends IView> implements IPresenter {

    private WeakReference<T> view;

    public BasePresenter(T iView) {
        if (iView != null) {
            view = new WeakReference<>(iView);
        }
    }


    /***
     * 销毁
     */
    public void onDestory() {
        if (view != null) {
            view.clear();
            view = null;
        }
    }
}
