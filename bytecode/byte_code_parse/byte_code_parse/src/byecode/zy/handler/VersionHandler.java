package byecode.zy.handler;

import byecode.zy.type.ClassFile;
import byecode.zy.type.U2;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * 版本号，副版本号和主版本号
 * 1.class文件结构的版本号分为主版本号和副版本号，它们共同构成class文件格式的版本号
 * 2.版本号解析器的职责就是从class文件字节缓存中读取出副版本号和主版本号。按顺序读取，先读取两个字节的副版本号，再读取两个字节的主版本号。
 *
 * @author zhangyu
 * @date 2023/1/15
 */
public class VersionHandler implements BaseByteCodeHandler {

    /**
     * 版本号和JDK版本号匹配，其中key是主版本号，value是JDK版本号
     */
    private static final Map<String, String> CLASS_VERSION_MAP = new HashMap<>(8);

    /**
     * 官方文档主版本号和JDK版本号映射关系
     * https://en.wikipedia.org/wiki/Java_class_file#General_layout:~:text=major%20version%20number%20of%20the%20class%20file%20format%20being%20used.%5B3%5D
     */
    static {
        CLASS_VERSION_MAP.put("45", "1.1");
        CLASS_VERSION_MAP.put("46", "1.2");
        CLASS_VERSION_MAP.put("47", "1.3");
        CLASS_VERSION_MAP.put("48", "1.4");
        CLASS_VERSION_MAP.put("49", "5");
        CLASS_VERSION_MAP.put("50", "6");
        CLASS_VERSION_MAP.put("51", "7");
        CLASS_VERSION_MAP.put("52", "8");
        CLASS_VERSION_MAP.put("53", "9");
        CLASS_VERSION_MAP.put("54", "10");
        CLASS_VERSION_MAP.put("55", "11");
        CLASS_VERSION_MAP.put("56", "12");
        CLASS_VERSION_MAP.put("57", "13");
        CLASS_VERSION_MAP.put("58", "14");
        CLASS_VERSION_MAP.put("59", "15");
        CLASS_VERSION_MAP.put("60", "16");
        CLASS_VERSION_MAP.put("61", "17");
        CLASS_VERSION_MAP.put("62", "18");
        CLASS_VERSION_MAP.put("63", "19");
    }


    @Override
    public int order() {
        return 1;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        U2 minorVersion = new U2(codeBuf.get(), codeBuf.get());
        classFile.setMinor_version(minorVersion);
        U2 majorVersion = new U2(codeBuf.get(), codeBuf.get());
        classFile.setMagor_version(majorVersion);
        System.out.println("版本号解析完毕，当前Class文件编译JDK版本为：" +
                CLASS_VERSION_MAP.get(String.valueOf(majorVersion.toInt())));
    }

}
