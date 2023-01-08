package com.zy.oom;

import java.util.ArrayList;
import java.util.Random;

/**
 * 测试堆内存溢出情况
 * 设置虚拟机参数：-Xmx100M
 *
 * @author zhangyu
 * @date 2022/10/22
 **/
public class OOMTest {
    public static void main(String[] args) {
        ArrayList<int[]> list = new ArrayList<>();
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new int[new Random().nextInt(1024 * 1024)]);
        }
    }
}