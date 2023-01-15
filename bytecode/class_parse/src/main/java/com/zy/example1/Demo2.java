package com.zy.example1;

public class Demo2 {
    public static void main(String[] args) {
        Object[] objects = new Object[1];
        System.out.println(objects); // [Ljava.lang.Object;@1cd072a9

        String[] strings = new String[1];
        System.out.println(strings);// [Ljava.lang.String;@7c75222b

        int[] ints = new int[1];
        System.out.println(ints);// [I@4c203ea1
    }
}
