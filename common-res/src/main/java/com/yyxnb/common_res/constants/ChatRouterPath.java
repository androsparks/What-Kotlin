package com.yyxnb.common_res.constants;

/**
 * ================================================
 * 作    者：yyx
 * 日    期：2021/03/16
 * 描    述：消息模块路由路径
 * ================================================
 */
public interface ChatRouterPath {

    /**
     * 模块一级路径
     */
    String MAIN_ROUTE = "/chat";

    /**
     * 服务
     */
    String SERVICE = MAIN_ROUTE + "/service";

    // ----------------------------------------------------------------------- 页面

    /**
     * 主Activity
     */
    String MAIN_ACTIVITY = MAIN_ROUTE + "/main_activity";
    /**
     * 主fragment
     */
    String MAIN_FRAGMENT = MAIN_ROUTE + "/main_fragment";
    /**
     * 对外展示
     */
    String SHOW_FRAGMENT = MAIN_ROUTE + "/show_fragment";

    String LIST_FRAGMENT = MAIN_ROUTE + "/list_fragment";


}
