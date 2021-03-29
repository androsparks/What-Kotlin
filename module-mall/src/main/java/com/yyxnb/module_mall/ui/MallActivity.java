package com.yyxnb.module_mall.ui;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yyxnb.common_base.base.ContainerActivity;
import com.yyxnb.common_res.constants.MallRouterPath;

/**
 * ================================================
 * 作    者：yyx
 * 日    期：2020/11/30
 * 描    述：商城 主界面
 * ================================================
 */
@Route(path = MallRouterPath.MAIN_ACTIVITY)
public class MallActivity extends ContainerActivity {

    @Override
    public Fragment initBaseFragment() {
        return new MallMainFragment();
    }
}