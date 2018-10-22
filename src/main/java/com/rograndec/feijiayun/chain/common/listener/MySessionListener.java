package com.rograndec.feijiayun.chain.common.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class MySessionListener implements SessionListener {
    @Override
    public void onStart(Session session) {
        System.out.println("登录");
    }

    @Override
    public void onStop(Session session) {
        System.out.println("登录退出");
    }

    @Override
    public void onExpiration(Session session) {

        System.out.println("登录过期-1==");
    }
}
