package com.zy.gc.monitor;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*******************************************************
 * Created by ZhangYu on 2024/8/14
 * Description :
 * History   :
 *******************************************************/


public class FullGCSimulator {

    // 用于存放对象的List，模拟内存泄漏或老年代填满的情况
    private static final List<Object> memoryLeakList = new ArrayList<>();

    public static void main(String[] args) {
        // 开启GC监控线程
        startGCMonitor();

        // 模拟Full GC的触发
        simulateFullGC();
    }

    // 模拟Full GC触发的代码
    private static void simulateFullGC() {
        try {
            while (true) {
                // 不断生成大对象并将其放入List中，防止被GC
                for (int i = 0; i < 10; i++) {
                    byte[] largeObject = new byte[1024 * 1024]; // 每个对象为1MB
                    memoryLeakList.add(largeObject);
                }
                // 短暂休眠，控制生成对象的速度
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // 开始监控Full GC的线程
    private static void startGCMonitor() {
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("------- GC Monitoring -------");
            for (GarbageCollectorMXBean gcBean : gcBeans) {
                String gcName = gcBean.getName();
                if (gcName.contains("Old") || gcName.contains("MarkSweepCompact") || gcName.contains("Tenured")) {
                    long fullGCCount = gcBean.getCollectionCount();
                    long fullGCTime = gcBean.getCollectionTime();

                    System.out.println("Full GC Name: " + gcName);
                    System.out.println("Full GC Count: " + fullGCCount);
                    System.out.println("Full GC Total Time (ms): " + fullGCTime);
                }
            }
            System.out.println("------------------------------");
        }, 0, 5, TimeUnit.SECONDS);
    }
}