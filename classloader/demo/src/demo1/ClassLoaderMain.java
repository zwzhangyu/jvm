package demo1;

public class ClassLoaderMain {

    // 使用ClassLoader.loadClass
    public static void loadClass1() throws ClassNotFoundException {
        Class<?> classLoaderTestClass = ClassLoaderMain.class.getClassLoader()
                .loadClass("demo1.ClassLoaderTest");
        System.out.println(classLoaderTestClass.getName());
    }

    public static void main(String[] args) throws ClassNotFoundException {
        loadClass1();
    }

    // 使用Class.forName
    public static void loadClass2() throws ClassNotFoundException {
        Class<?> classLoaderTestClass = Class.forName(
                "com.wujiuye.asmbytecode.book.fourth.ClassLoaderTest");
        System.out.println(classLoaderTestClass.getName());
    }

}
