package com.ssl.common.mvp.app

import android.view.View

class MainActivity : AppBaseActivity() {
    override fun initView() {
        setTitle("我的标题")
    }

    override fun initEvent() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main;
    }

    public fun myOnClick(view: View) {
        when (view.id) {
            R.id.dialogLoading -> {
                showDialogLoading()
            }
            R.id.inLoading -> {
                showInsideLoading()
            }
        }
    }

}
