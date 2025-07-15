package com.accounttranfer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TestXorant {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4);
        Optional<Integer> num =list.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst();
        num.ifPresent(System.out::println);
//        if(num.isPresent()){
//            System.out.println("Second highest num ::"+num);
//        };

    }

    //lambda expresion calculator
}
