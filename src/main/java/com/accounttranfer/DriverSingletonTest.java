package com.accounttranfer;

public class DriverSingletonTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        SingletonClassTest singletonClassTest = SingletonClassTest.getInstance();
        singletonClassTest.show();
        SingletonClassTest singletonClassTest1 = SingletonClassTest.getInstance();
        System.out.println(singletonClassTest);
        System.out.println(singletonClassTest1);

        SingletonClassTest s1 = SingletonClassTest.getInstance();
        SingletonClassTest s2 = (SingletonClassTest) s1.clone();
        System.out.println("s1"+s1);
        System.out.println("s2" +s2);
        System.out.println(s1 == s2);

    }
}
