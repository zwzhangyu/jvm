package byecode.zy.test;

/**
 * 需要被解析class文件结构的测试类
 */
public class Demo implements Runnable {

    private int num = 1;

    public int add() {
        num = num + 2;
        return num;
    }

    @Override
    public void run() {

    }
}
