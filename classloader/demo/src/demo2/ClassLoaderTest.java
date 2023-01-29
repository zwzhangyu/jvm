package demo2;


public class ClassLoaderTest {
    public static void main(String[] args) {
        //Object类的加载只能使用引导类加载器进行！
        Object object = new Object();
    }
}
