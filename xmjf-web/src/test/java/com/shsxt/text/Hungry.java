package com.shsxt.text;

/**
 * 饿汉式单例
 * Created by Administrator on 2018/10/8.
 */
public class Hungry{
    private static Hungry hungry = new Hungry();
    private Hungry(){

    }
    private static Hungry getInstance(){
        return hungry;
    }
}
