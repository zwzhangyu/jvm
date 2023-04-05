package com.zy.demo2;

public class User {

    public void hello() {
        try {
            System.out.println("开始执行方法");
            Thread.sleep(3000);
            System.out.println("方法执行结束");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
