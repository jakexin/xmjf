package com.shsxt.text;

/**
 * Created by Administrator on 2018/10/8.
 */

//懒汉式单例模式

public class Single{
    private static Single single = null;
    private Single(){
        System.out.println("haha");
    }
    private static Single getInstance(){

        if (single==null){
            single = new Single();
        }
        return single;
    }

}

