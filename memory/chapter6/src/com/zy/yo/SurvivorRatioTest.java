package com.zy.yo;

/**
 * 【测试场景1】设置最大堆内存300M，通过jvisualvm.exe工具查看新生代和老年代占比
 * 【测试场景2】设置JVM参数-XX:NewRatio，修改占比，再查看内存情况
 *
 * @author zhangyu
 * @date 2022/10/22
 **/
public class SurvivorRatioTest {
    public static void main(String[] args) {
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
