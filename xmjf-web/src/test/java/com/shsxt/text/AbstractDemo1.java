package com.shsxt.text;

/**
 * Created by Administrator on 2018/10/9.
 */
public class AbstractDemo1 {
    public static void main(String[] args) {

    }
}
//定义一个抽象类
abstract class Animal{

    String name;
    int age;
    //抽象方法
    public abstract void cry();
}
//继承一个抽象类，实现并重写所有抽象方法
class cat extends Animal{

    @Override
    public void cry() {

        System.out.println("喵喵~");
    }
}



