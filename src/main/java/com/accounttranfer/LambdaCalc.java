package com.accounttranfer;

public class LambdaCalc {
    public static void main(String[] args) {
        Calculator add = (a,b) -> a+b;
        Calculator subtract = (a,b) -> a-b;
        Calculator multiply = (a,b) -> a*b;

        double val1 = 20 , val2 = 10;

        System.out.println("Add:"+add.operate(val1,val2));
        System.out.println("Add:"+subtract.operate(val1,val2));
        System.out.println("Add:"+multiply.operate(val1,val2));
    }
}
