package algorithm.ref_count_gc;


/**
 * 测试Java中是否采用的是引用计数算法
 * -XX:+PrintGCDetails
 *
 * @author zhangyu
 * @date 2023/1/8
 */
public class RefCountGC {
    // 这个成员属性的唯一作用就是占用一点内存,5M
    private byte[] bigSize = new byte[5 * 1024 * 1024];
    // 引用
    Object reference = null;

    public static void main(String[] args) {
        RefCountGC obj1 = new RefCountGC();
        RefCountGC obj2 = new RefCountGC();

        obj1.reference = obj2;
        obj2.reference = obj1;

        obj1 = null;
        obj2 = null;
        // 显示的执行垃圾收集行为
        // 这里发生GC，obj1和obj2是否被回收？
//        System.gc();
    }
}