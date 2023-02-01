package bytecode_classloader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义类加载器ByteCodeClassLoader的实现
 *
 * @author zhangyu
 * @date 2023/1/30
 */
public class ByteCodeClassLoader extends ClassLoader {

    private final Map<String, ByteCodeHandler> classes = new HashMap<>();

    public ByteCodeClassLoader(final ClassLoader parentClassLoader) {
        super(parentClassLoader);
    }

    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        ByteCodeHandler handler = classes.get(name);
        if (handler != null) {
            byte[] bytes = handler.getByteCode();
            try {
                ByteCodeUtils.savaToClasspath(handler.getClassName(), bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return defineClass(name, bytes, 0, bytes.length);
        }
        return super.findClass(name);
    }

    public void add(final String name, final ByteCodeHandler handler) {
        classes.put(name.replace("/", "."), handler);
    }

    /**
     * 加载类
     *
     * @param name 全类名
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        name = name.replace("/", ".");
        return super.loadClass(name);
    }

}