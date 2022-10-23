package com.zy.gc_log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 观察Minor GC、Major GC以及Full GC日志信息
 * [设置JVM参数] -Xms9m -Xmx9m -XX:+PrintGCDetails
 *
 * @author zhangyu
 * @date 2022/10/23
 **/
public class PrintGcDetailsTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        String temp = "Hello World!!!!";
        while (true) {
            // 不断扩展下一次加入集合的字符串对象大小，以此来促使JVM发生GC留出内存放置对象
            temp += new Random().nextInt();
            list.add(temp);
        }
    }
}
