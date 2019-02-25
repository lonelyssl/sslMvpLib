package com.ssl.common.mvp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ssl.common.mvp.base.IPresenter;
import com.ssl.common.mvp.base.IView;

import java.util.HashSet;
import java.util.Set;

/***
 * Author:shishaolong
 * Date:2019/1/17
 *
 */
public abstract class BaseMvpActivity extends AppCompatActivity implements IView {

    /**
     * 标题视图
     */
    private FrameLayout titleView;
    /**
     * 默认的标题视图
     */
    private ViewStub defTitleView;

    /**
     * 中间标题栏
     */
    private TextView centerTitleView;

    /**
     * 中间视图
     */
    private FrameLayout centerView;

    /**
     * 内部加载视图
     */
    private FrameLayout insideView;
    /**
     * 外部提供的
     */
    private View childInsideView;


    /***
     * 当前Activity所使用的所有P层集合
     */
    private Set<IPresenter> presenters = new HashSet<>();


    /**
     * 弹出框
     */
    private Dialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeSetContentView();
        setContentView(R.layout.ssl_base_activity_root_layout);
        titleView = find(R.id.ssl_titleBar);
        defTitleView = find(R.id.ssl_default_titleBar);
        centerView = find(R.id.ssl_center);
        insideView = find(R.id.ssl_inside_load);
        initTitleBar();
        initCenterView();
        init();
    }

    public void beforeSetContentView() {
    }


    public void init() {
        initPassParams();
        initView();
        initEvent();
    }

    /**
     * 处理View
     */
    protected abstract void initView();

    /**
     * 处理逻辑与监听事件
     */
    protected abstract void initEvent();

    public void initPassParams() {
    }

    public void initCenterView() {
        View view = LayoutInflater.from(this).inflate(getLayoutId(), centerView, false);
        centerView.addView(view, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    }

    /**
     * 主的布局文件
     *
     * @return
     */
    public abstract int getLayoutId();


    /**
     * 隐藏标题
     */
    public void hideTitleBar() {
        if (titleView != null) {
            titleView.setVisibility(View.GONE);
        }
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (centerTitleView != null) {
            centerTitleView.setText(title);
        }

    }

    /**
     * 初始化标题
     */
    public final void initTitleBar() {
        View temView = getCustomTitleBarView();
        if (temView == null) {
            defTitleView.inflate();
            centerTitleView = find(R.id.ssl_tv_title);
        } else {
            defTitleView.setVisibility(View.GONE);
            titleView.addView(temView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }
    }


    /**
     * 用户自定义的标题视图；返回null表示使用默认的标题
     *
     * @return
     */
    public View getCustomTitleBarView() {
        return null;
    }


    public <T extends View> T find(@IdRes int id) {
        return findViewById(id);
    }


    @Override
    public void addPresenter(IPresenter p) {
        presenters.add(p);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (IPresenter iP : presenters) {
            iP.onDestory();
        }
    }

    /***************/

    @Override
    public void showDialogLoading() {
        if (mDialog == null) {
            mDialog = getDialogLoading();
        }
        if (mDialog == null) {
            mDialog = new ProgressDialog(this);
            ((ProgressDialog) mDialog).setMessage(getString(R.string.ssl_base_load_dialog_msg));
        }
        mDialog.show();
    }


    /**
     * 用户自定义loading弹出框
     *
     * @return
     */
    public Dialog getDialogLoading() {
        return null;
    }

    /**
     * @return
     */
    public View getInsideLoading() {
        return null;
    }

    @Override
    public void showInsideLoading() {
        if (insideView != null) {
            insideView = find(R.id.ssl_inside_load);
            if (childInsideView == null) {
                childInsideView = getInsideLoading();
            }
        }
        if (childInsideView == null) {
            centerView.setVisibility(View.VISIBLE);
            insideView.setVisibility(View.GONE);
        } else {
            centerView.setVisibility(View.GONE);
            insideView.setVisibility(View.VISIBLE);
            insideView.addView(childInsideView,new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }

    }

    @Override
    public void dismissLoading() {

    }
}
