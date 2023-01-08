package zy.demo1;

public class ClassLoaderTest {
    public static void main(String[] args) {
        // 获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器：" + systemClassLoader); // sun.misc.Launcher$AppClassLoader
        // 获取其父级，扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println("扩展类加载器：" + extClassLoader); // sun.misc.Launcher$ExtClassLoader
        // 在往上级获取，引导类加载器
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println("引导类加载器：" + bootstrapClassLoader); // null
        // 查看自定义类的类加载器，对于自定义类而言使用默认是系统类加载器
        System.out.println(ClassLoaderTest.class.getClassLoader()); // sun.misc.Launcher$AppClassLoader
        // 查看java.lang包下类的类加载器,系统的核心类库使用的是引导类加载器
        System.out.println(String.class.getClassLoader());  // null
    }
}
