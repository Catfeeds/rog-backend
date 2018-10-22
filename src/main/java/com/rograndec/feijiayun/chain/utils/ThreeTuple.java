package com.rograndec.feijiayun.chain.utils;

/**
 * 功能描述：
 * Created by ST on 2017/10/18 11:57
 */

public class ThreeTuple<A,B,C> extends TwoTuple<A,B>{
    public final C third;

    public ThreeTuple(A a, B b, C c) {
        super(a, b);
        third = c;
    }
}