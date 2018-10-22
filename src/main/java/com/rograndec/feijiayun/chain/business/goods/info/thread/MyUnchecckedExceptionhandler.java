package com.rograndec.feijiayun.chain.business.goods.info.thread;

import com.rograndec.feijiayun.chain.exception.BusinessException;

public class MyUnchecckedExceptionhandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("捕获到异常：" + e);
        throw new BusinessException("商品导入异常");
    }

}