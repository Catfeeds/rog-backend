package com.rograndec.feijiayun.chain.business.goods.info.thread;

import java.util.concurrent.ThreadFactory;

public class HandlerThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        System.out.println("创建一个新的线程");
        Thread t = new Thread(r);
        t.setUncaughtExceptionHandler(new MyUnchecckedExceptionhandler());
        System.out.println("eh121 = " + t.getUncaughtExceptionHandler());
        return t;
    }

}