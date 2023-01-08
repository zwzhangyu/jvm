package com.zy.dgc;

import java.util.ArrayList;
import java.util.Random;

/**
 * 代码举例与JVisualVM演示对象
 * 1.设置-Xmx100M
 * 2.启动程序，不断增加内存使用
 * 3.观察JVisualVM
 *
 * @author zhangyu
 * @date 2022/10/22
 **/
public class DynamicGcTest {
    public static void main(String[] args) {
        ArrayList<int[]> list = new ArrayList<>();
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new int[new Random().nextInt(1024 * 20)]);
        }
    }
}
