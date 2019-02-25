package com.ssl.common.mvp.base;

/***
 * Author:shishaolong
 * Date:2019/1/17
 * V层父接口
 */
public interface IView {


    public void addPresenter(IPresenter p);

    /**
     * 显示弹出Loading
     */
    public void showDialogLoading();


    /**
     * 布局内Loading
     */
    public void showInsideLoading();


    /**
     * 隐藏loading视图
     */
    public void dismissLoading();


}
