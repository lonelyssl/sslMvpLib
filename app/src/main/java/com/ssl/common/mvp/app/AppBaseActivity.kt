package com.ssl.common.mvp.app

import android.view.View
import android.view.Window
import com.ssl.common.mvp.BaseMvpActivity

abstract class AppBaseActivity : BaseMvpActivity() {

    override fun initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    override fun getInsideLoading(): View {
        return View.inflate(this, R.layout.custom_inside_loading,null);

    }


}
