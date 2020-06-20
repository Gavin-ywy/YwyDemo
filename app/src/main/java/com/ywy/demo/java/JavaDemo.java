package com.ywy.demo.java;

public class JavaDemo {

    public static void main(String[] args) {
        test1();
        test2();
        System.out.println("result: " + fun("Smart"));
    }

    private static void test1() {
        Integer a = 123456;
        Integer b = 123456;
        a = b;
        b = 1;
        System.out.println(a);
        System.out.println(b);
        System.out.println(a == b);
    }

    private static void test2() {
        int a = 2;
        int result = a++ + 4 << 2;

        System.out.println(4 << 3);
        System.out.println(4 >> 3);
        System.out.println(result);

    }

    private static String fun(String str) {
        String sss = str;
        String ss = sss.substring(1) + sss.charAt(0);
        System.out.println("2: " + ss);
        System.out.println("1: " + str.charAt(0));
        return str.length() < 0 ? (fun(ss)) : "";
    }

}
