package com.zy.gc.monitor;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/*******************************************************
 * Created by ZhangYu on 2024/8/14
 * Description :
 * History   :
 *******************************************************/

public class GCMonitor {
    public static void main(String[] args) {
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcBean : gcBeans) {
            String gcName = gcBean.getName();
            if (gcName.contains("Old") || gcName.contains("MarkSweepCompact")) {
                System.out.println("Monitoring Full GC: " + gcName);
                System.out.println("Full GC count: " + gcBean.getCollectionCount());
                System.out.println("Full GC time (ms): " + gcBean.getCollectionTime());
            }
        }
    }
}
