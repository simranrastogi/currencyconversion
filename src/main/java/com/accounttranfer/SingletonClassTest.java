package com.accounttranfer;

public class SingletonClassTest implements  Cloneable{
    private static volatile SingletonClassTest sigleton_instance;

    private SingletonClassTest(){}

    public static SingletonClassTest getInstance(){

        if(sigleton_instance == null){
            synchronized (SingletonClassTest.class){
                if( sigleton_instance == null){
                    sigleton_instance = new SingletonClassTest();
                }
            }
        } return  sigleton_instance;
    }

    public void show(){
        System.out.println("Hello singleton");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        //new CloneNotSupportedException("Clone not allowed");
        return super.clone();
    }
}
